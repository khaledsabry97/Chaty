package com.example.khaledsabry.Client.Controllers;

import com.example.khaledsabry.Client.Fragments.CreateRoomFragment;

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


    public void roomCreatedSuccessfully(int roomId)
    {
        //if(createRoomFragment.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED))
        {
            createRoomFragment.created(roomId);
        }
    }


    public void roomCreatedUnSuccessfully(String msg)
    {
        createRoomFragment.notCreated(msg);
    }

    public void roomCreatedFound(String msg)
    {
        createRoomFragment.notCreated(msg);

    }


    /*Join Room*/

    public void joinRoom(String roomName,String roomPassword,String nickName)
    {
        msgEncoder.joinRoom(roomName,roomPassword,nickName);
    }

    public void roomJoinSuccess(int roomId)
    {
        createRoomFragment.Joined(roomId);

    }

    public void roomJoinUnSuccess(String msg)
    {
        createRoomFragment.notJoined(msg);

    }

    public void roomJoinNotFound(String msg)
    {
        createRoomFragment.notJoined(msg);
    }


}
