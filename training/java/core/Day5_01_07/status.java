package Day5_01_07;


enum Status {
    SUCCESS(200), NOT_FOUND(404), SERVER_ERROR(500);
    private final int code;
    Status(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}

