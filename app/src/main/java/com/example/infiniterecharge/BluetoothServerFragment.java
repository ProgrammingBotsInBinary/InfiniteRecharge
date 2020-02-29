package com.example.infiniterecharge;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import static com.example.infiniterecharge.Variables.*;


public class BluetoothServerFragment extends Fragment {
    private static final String TAG = "BluetoothServerFragment";

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    // Layout Views
    private ListView mConversationView;
    private EditText mOutEditText;
    private Button mSendButton;
    private Button mConnectButton;
    private Button mDiscoverButton;

    /**
     * Name of the connected device
     */
    private String mConnectedDeviceName = null;

    /**
     * Array adapter for the conversation thread
     */
    private ArrayAdapter<String> mConversationArrayAdapter;

    /**
     * String buffer for outgoing messages
     */
    private StringBuffer mOutStringBuffer;

    /**
     * Local Bluetooth adapter
     */
    private BluetoothAdapter mBluetoothAdapter = null;

    /**
     * Member object for the chat services
     */
    private BluetoothConnectionService mChatService = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            FragmentActivity activity = getActivity();
            Toast.makeText(activity, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            activity.finish();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            // Otherwise, setup the chat session
        } else if (mChatService == null) {
            setupChat();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChatService != null) {
            mChatService.stop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (mChatService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService.getState() == BluetoothConnectionService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService.start();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bluetooth_server, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mConversationView = (ListView) view.findViewById(R.id.in);
        mOutEditText = (EditText) view.findViewById(R.id.edit_text_out);
        mSendButton = (Button) view.findViewById(R.id.button_send);
        mConnectButton = (Button) view.findViewById(R.id.button_connect);
        mDiscoverButton = (Button) view.findViewById(R.id.button_discoverable);

    }

    /**
     * Set up the UI and background operations for chat.
     */
    private void setupChat() {
        Log.d(TAG, "setupChat()");

        // Initialize the array adapter for the conversation thread
        mConversationArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.message);

        mConversationView.setAdapter(mConversationArrayAdapter);

        // Initialize the compose field with a listener for the return key
        mOutEditText.setOnEditorActionListener(mWriteListener);

        // Initialize the send button with a listener that for click events
        mSendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    sendMessage();
                    mChatService.stop();
                }
            }
        });

        mDiscoverButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget

                View view = getView();
                if (null != view) {
                    // Ensure this device is discoverable by others
                    ensureDiscoverable();
                }

            }
        });

        mConnectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                View view = getView();
                if (null != view) {
                    Intent serverIntent = new Intent(getActivity(), DeviceListActivity.class);
                    startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
                }
            }
        });



        // Initialize the BluetoothConnectionService to perform bluetooth connections
        mChatService = new BluetoothConnectionService(getActivity(), mHandler);

        // Initialize the buffer for outgoing messages
        mOutStringBuffer = new StringBuffer("");
    }

    /**
     * Makes this device discoverable for 300 seconds (5 minutes).
     */
    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }

    /**
     * Sends a message.
     *
     */
    private void sendMessage() {
        // Check that we're actually connected before trying anything
        if (mChatService.getState() != BluetoothConnectionService.STATE_CONNECTED) {
            Toast.makeText(getActivity(), R.string.not_connected, Toast.LENGTH_SHORT).show();
            return;
        }

        DbHelper myDb = new DbHelper(getContext());
        Cursor cur = myDb.getData();
        toSend += "Server";
        try {
            for(int i = 0; i < cur.getCount(); i++) {
                cur.moveToNext();
                String stuff = cur.getString(cur.getColumnIndex(Col1));
                if(stuff != null) {
                    Log.d("BLUETOOTH", "Makes it to tosend ");
                    toSend += delimiter + cur.getString(cur.getColumnIndex(Col1)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col2)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col3)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col4)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col5)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col6)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col7)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col8)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col9)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col10)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col11)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col12)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col13)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col14)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col15)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col16)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col17)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col18)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col19)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col20)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col21)) + delimiter +
                            cur.getString(cur.getColumnIndex(Col22));

                }
                else
                    stuff = "This shit was null and why it was failing";
                Log.d("CURSOR STUFF", stuff);
            }
        } finally {
            cur.close();
        }

        Log.d("FULL STRINGS", toSend);
        // Check that there's actually something to send
        if (toSend.length() > 0) {

            // Get the message bytes and tell the BluetoothConnectionService to write
            byte[] send = toSend.getBytes();
            mChatService.write(send);

            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);
            toSend = "";
        }
    }

    /**
     * The action listener for the EditText widget, to listen for the return key
     */
    private TextView.OnEditorActionListener mWriteListener
            = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
            // If the action is a key-up event on the return key, send the message
            if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP) {
                String message = view.getText().toString();
                //sendMessage(message);
            }
            return true;
        }
    };

    /**
     * Updates the status on the action bar.
     *
     * @param resId a string resource ID
     */
    private void setStatus(int resId) {
        FragmentActivity activity = getActivity();
        if (null == activity) {
            return;
        }
        final ActionBar actionBar = activity.getActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.setSubtitle(resId);
    }

    /**
     * Updates the status on the action bar.
     *
     * @param subTitle status
     */
    private void setStatus(CharSequence subTitle) {
        FragmentActivity activity = getActivity();
        if (null == activity) {
            return;
        }
        final ActionBar actionBar = activity.getActionBar();
        if (null == actionBar) {
            return;
        }
        actionBar.setSubtitle(subTitle);
    }

    /**
     * The Handler that gets information back from the BluetoothConnectionService
     */
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            FragmentActivity activity = getActivity();
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothConnectionService.STATE_CONNECTED:
                            setStatus(getString(R.string.title_connected_to, mConnectedDeviceName));
                            mConversationArrayAdapter.clear();
                            break;
                        case BluetoothConnectionService.STATE_CONNECTING:
                            setStatus(R.string.title_connecting);
                            break;
                        case BluetoothConnectionService.STATE_LISTEN:
                        case BluetoothConnectionService.STATE_NONE:
                            setStatus(R.string.title_not_connected);
                            break;
                    }
                    break;
                case MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    mConversationArrayAdapter.add("Me:  " + writeMessage);
                    break;
                case MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    input = readMessage;

                    List<String> display = Arrays.asList(input.split(delimiter));
                    Log.d("First entry is: ", readMessage);
                    //We know it's going from a scout to lead scout
                    if(!display.get(0).equals("Server")) {
                        String displayMessage = "\n" +
                                "Team ID: " + display.get(0) + "\n" +
                                "Match ID: " + display.get(1) + "\n" +
                                "Auton Lvl1: " + display.get(2) + "\n" +
                                "Auton Lvl2: " + display.get(3) + "\n" +
                                "Auton Lvl3: " + display.get(4) + "\n" +
                                "Auton Line: " + display.get(5) + "\n" +
                                "Auton Pos.: " + display.get(6) + "\n" +
                                "Teleop S Lvl1: " + display.get(7) + "\n" +
                                "Teleop S Lvl2: " + display.get(8) + "\n" +
                                "Teleop S Lvl3: " + display.get(9) + "\n" +
                                "Teleop F Lvl1: " + display.get(10) + "\n" +
                                "Teleop F Lvl2: " + display.get(11) + "\n" +
                                "Teleop F Lvl3: " + display.get(12) + "\n" +
                                "Rot. Control: " + display.get(13) + "\n" +
                                "Pos. Control: " + display.get(14) + "\n" +
                                "Endgame: " + display.get(15) + "\n" +
                                "Rotation Time: " + display.get(16) + "\n" +
                                "Pos. Time: " + display.get(17) + "\n" +
                                "Cycles: " + display.get(18) + "\n" +
                                "Def. Played : " + display.get(19) + "\n" +
                                "Def. Played On : " + display.get(20) + "\n" +
                                "Notes: " + display.get(21) + "\n";

                        mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + displayMessage);

                        //automatically input results into server side db
                        List<String> received = Arrays.asList(input.split(delimiter));
                        Log.d(TAG, "This is the inputted string:" + input);
                        DbHelper myDb = new DbHelper(getContext());
                        myDb.insertData(Integer.parseInt(received.get(0)), Integer.parseInt(received.get(1)), Integer.parseInt(received.get(2)), Integer.parseInt(received.get(3)),
                                Integer.parseInt(received.get(4)), Integer.parseInt(received.get(5)), Integer.parseInt(received.get(6)), Integer.parseInt(received.get(7)),
                                Integer.parseInt(received.get(8)), Integer.parseInt(received.get(9)), Integer.parseInt(received.get(10)), Integer.parseInt(received.get(11)),
                                Integer.parseInt(received.get(12)), Integer.parseInt(received.get(13)), Integer.parseInt(received.get(14)), Integer.parseInt(received.get(15)),
                                Integer.parseInt(received.get(16)), Integer.parseInt(received.get(17)), Integer.parseInt(received.get(18)), Integer.parseInt(received.get(19)),
                                Integer.parseInt(received.get(20)), received.get(21));
                        ;
                    }

                    //IF IT BEGINS WITH "Server" and is there going from lead scout to drive coach
                    else{
                        /**
                         *
                         * Loop through each "set/row" within the string
                         * Each set/row is 35 columns long
                         * On first pass through, don't start from 0 for team number
                         * Start from 1 due to "Server" identifier
                         * Every iteration past that you can start at 0 like above
                         * After each iteration, insert into the local database
                         * Before passed from server to server, must delete pre existing database to prevent duplicate data
                         *
                         */
                        DbHelper myDb = new DbHelper(getContext());
                        List<String> received = display;
                        Log.d("Received size ",  Integer.toString(received.size()));
                        for(int i = 0; i < (received.size()/22); i++){
                            Log.d("Entry 1: ", Integer.toString((i*22) + 1));
                            Log.d("Entry 2: ", Integer.toString((i*22) + 2));
                            Log.d("Entry 3: ", Integer.toString((i*22) + 3));
                            myDb.insertData(Integer.parseInt(received.get((i*22)+ 1)), Integer.parseInt(received.get((i*22) + 2)), Integer.parseInt(received.get((i*22) + 3)),
                                    Integer.parseInt(received.get((i*22) + 4)), Integer.parseInt(received.get((i*22) + 5)), Integer.parseInt(received.get((i*22) + 6)),
                                    Integer.parseInt(received.get((i*22) + 7)),
                                    Integer.parseInt(received.get((i*22) + 8)), Integer.parseInt(received.get((i*22) + 9)), Integer.parseInt(received.get((i*22) + 10)),
                                    Integer.parseInt(received.get((i*22) + 11)),
                                    Integer.parseInt(received.get((i*22) + 12)), Integer.parseInt(received.get((i*22) + 13)), Integer.parseInt(received.get((i*22) + 14)),
                                    Integer.parseInt(received.get((i*22) + 15)),
                                    Integer.parseInt(received.get((i*22) + 16)), Integer.parseInt(received.get((i*22) + 17)), Integer.parseInt(received.get((i*22) + 18)),
                                    Integer.parseInt(received.get((i*22) + 19)), Integer.parseInt(received.get(i*22) + 20), Integer.parseInt(received.get(i*22) + 21),
                                    received.get((i*22) + 22));

                        }
                        /*
                        Log.d("Returned Num of Matches", Integer.toString(myDb.getNumOfMatches(3604)));
                        Log.d("Return number of Hab", myDb.getNumOfLeaveHab(3604));
                        Log.d("Return number of C", myDb.getNumofCargo(3604));
                        Log.d("Return number of H", myDb.getNumOfHatchPanels(3604));
                        Log.d("Return number of H/C", myDb.getNumOfHatchandCargo(3604));
                        Log.d("Return number of climb", myDb.getNumOfClimb(3604));
                        */


                        mConversationArrayAdapter.add(mConnectedDeviceName + ": I successfully received a copy of the database");
                    }

                    break;
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    if (null != activity) {
                        Toast.makeText(activity, "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case MESSAGE_TOAST:
                    if (null != activity) {
                        Toast.makeText(activity, msg.getData().getString(TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE_SECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, true);
                }
                break;
            case REQUEST_CONNECT_DEVICE_INSECURE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    connectDevice(data, false);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a chat session
                    setupChat();
                } else {
                    // User did not enable Bluetooth or an error occurred
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(getActivity(), R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
        }
    }

    /**
     * Establish connection with other device
     *
     * @param data   An {@link Intent} with {@link DeviceListActivity#EXTRA_DEVICE_ADDRESS} extra.
     * @param secure Socket Security type - Secure (true) , Insecure (false)
     */
    private void connectDevice(Intent data, boolean secure) {
        // Get the device MAC address
        String address = data.getExtras()
                .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
        // Get the BluetoothDevice object
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        // Attempt to connect to the device
        mChatService.connect(device, secure);
    }
}
