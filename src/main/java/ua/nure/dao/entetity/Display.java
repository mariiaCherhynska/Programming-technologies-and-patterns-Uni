package ua.nure.dao.entetity;

public class Display {
    private int id;
    private int screenRefreshRate;
    private String matrixType;

    public Display(int id, int screenRefreshRate, String matrixType) {
        this.id = id;
        this.screenRefreshRate = screenRefreshRate;
        this.matrixType = matrixType;
    }
    public Display(int screenRefreshRate, String matrixType) {
        this.screenRefreshRate = screenRefreshRate;
        this.matrixType = matrixType;
    }

    @Override
    public String toString() {
        return "Display{" +
                "id=" + id +
                ", screenRefreshRate='" + screenRefreshRate + '\'' +
                ", matrixType='" + matrixType + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScreenRefreshRate() {
        return screenRefreshRate;
    }

    public void setScreenRefreshRate(int screenRefreshRate) {
        this.screenRefreshRate = screenRefreshRate;
    }

    public String getMatrixType() {
        return matrixType;
    }

    public void setMatrixType(String matrixType) {
        this.matrixType = matrixType;
    }

    public Display() {
    }

    public static class Builder{
        private int id;
        private int screenRefreshRate;
        private String matrixType;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setScreenRefreshRate(int screenRefreshRate) {
            this.screenRefreshRate = screenRefreshRate;
            return this;
        }
        public Builder setMatrixType(String matrixType) {
            this.matrixType = matrixType;
            return this;
        }
        public Display build(){
            return new Display(id, screenRefreshRate, matrixType);
        }
    }
}
