package api;

public enum StatusCodes {

    OK(200),
    EMPTY(404),
    POST(201);

    private final int STATUS;

    StatusCodes(int status) {
        this.STATUS = status;
    }

    public int getSTATUS() {
        return STATUS;
    }
}