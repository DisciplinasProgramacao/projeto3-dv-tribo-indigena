public enum Vars {
    FILE_DATA("data.dat");

    private final String code;

    Vars(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
