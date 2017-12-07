package com.wzm.im.imbean;

/**
 * Created by Administrator on 2017/5/13 0013.
 */

public class EMMessage {
    private String type;
    private String fromId;
    private String toId;
    private String chatType;
    private long timetamps;
    private String direction;
    private String content;
    private String msgId;
    private String title;
    private String status;
    private String size;
    private String fileId;
    private String ext;

    private boolean listened;

    public boolean isListened() {
        return listened;
    }

    public void setListened(boolean listened) {
        this.listened = listened;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public Direct direct() {
        return this.direction.equals("SEND")? Direct.SEND: Direct.RECEIVE;
    }


    public long getTimetamps() {
        return timetamps;
    }

    public void setTimetamps(long timetamps) {
        this.timetamps = timetamps;
    }

    public Type type(){
        if(this.type.equals("txt")){
            return Type.TXT;
        }else if(this.type.equals("image")){
            return Type.IMAGE;
        }else if(this.type.equals("voice")){
            return Type.VOICE;
        }else if(this.type.equals("video")){
            return Type.VIDEO;
        }else if(this.type.equals("file")){
            return Type.FILE;
        }
        return null;
    }

    public ChatType chatType(){
        return this.chatType.equals("0")? ChatType.Chat: ChatType.GroupChat;
    }

    public Status status(){
        Status status=null;

        if(this.status.equals("0")){
            return Status.SUCCESS;
        }else if(this.status.equals("1")){
            return Status.INPROGRESS;
        }else if(this.status.equals("2")){
            return Status.FAIL;
        }
        return status;

    }
    public static enum ChatType {
        Chat,
        GroupChat,
        ChatRoom;

        private ChatType() {
        }
    }

    public static enum Status {
        SUCCESS,
        FAIL,
        INPROGRESS,
        CREATE;

        private Status() {
        }
    }

    public static enum Direct {
        SEND,
        RECEIVE;

        private Direct() {
        }
    }

    public static enum Type {
        TXT,
        IMAGE,
        VIDEO,
        LOCATION,
        VOICE,
        FILE,
        CMD;

        private Type() {
        }
    }

    @Override
    public String toString() {
        return "EMMessage{" +
                "type='" + type + '\'' +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", chatType='" + chatType + '\'' +
                ", timetamps=" + timetamps +
                ", direction='" + direction + '\'' +
                ", content='" + content + '\'' +
                ", msgId='" + msgId + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", size='" + size + '\'' +
                ", fileId='" + fileId + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }
}

