package com.example.khaledsabry.Client.Controllers;

import com.example.khaledsabry.Client.Fragments.CreateRoomFragment;

/**
 * responsible for all the functionlaties of creating and joining the rooms
 */
public class SignInUpControlller extends Controller {

    CreateRoomFragment createRoomFragment;
    private static final SignInUpControlller ourInstance = new SignInUpControlller();

    public static SignInUpControlller getInstance() {
        return ourInstance;
    }

    private SignInUpControlller() {


    }


    public void setCreateRoomFragment(CreateRoomFragment createRoomFragment)
    {
        this.createRoomFragment = createRoomFragment;
    }

    /**
     * request to create room
     * @param roomName
     * @param roomPassword
     * @param nickName
     */
    public void createRoom(String roomName,String roomPassword,String nickName)
    {
        msgEncoder.createRoom(roomName,roomPassword,nickName);
    }

    /**
     * when you create the room and receive success
     * @param roomId room id to store
     */
    public void roomCreatedSuccessfully(int roomId)
    {
        //if(createRoomFragment.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED))
        {
            createRoomFragment.created(roomId);
        }
    }

    /**
     * when you create a room and doesn't be created
     * @param msg the problem
     */
    public void roomCreatedUnSuccessfully(String msg)
    {
        createRoomFragment.notCreated(msg);
    }

    /**
     * when you create a room and there is another room with the same name and password
     * @param msg the problem
     */
    public void roomCreatedFound(String msg)
    {
        createRoomFragment.notCreated(msg);

    }


    /*Join Room*/

    /**
     * when you enter on join room
     * @param roomName room name to join
     * @param roomPassword room password to join
     * @param nickName your nickname
     */
    public void joinRoom(String roomName,String roomPassword,String nickName)
    {
        msgEncoder.joinRoom(roomName,roomPassword,nickName);
    }

    /**
     * room name and password was correct now you can join
     * @param roomId room id
     */
    public void roomJoinSuccess(int roomId)
    {
        createRoomFragment.Joined(roomId);

    }

    /**
     * there went something wrong on the server
     * @param msg the problem
     */
    public void roomJoinUnSuccess(String msg)
    {
        createRoomFragment.notJoined(msg);

    }

    /**
     * room name/password wasn't right
     * @param msg the problem
     */
    public void roomJoinNotFound(String msg)
    {
        createRoomFragment.notJoined(msg);
    }


}
