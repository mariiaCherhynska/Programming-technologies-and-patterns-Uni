package ua.nure.dao.entetity;

public class Phone {
    private int id;
    private String model;
    private Processor processor;
    private Display display;

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", processor=" + processor +
                ", display=" + display +
                '}';
    }

    private Phone(int id, String model, Processor processor, Display display) {
        this.id = id;
        this.model = model;
        this.processor = processor;
        this.display = display;
    }
    private Phone(String model, Processor processor, Display display) {
        this.model = model;
        this.processor = processor;
        this.display = display;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

     public static class Builder{
        private int id;
        private String model;
        private Processor processor;
        private Display display;

        public Builder(){}


        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setModel(String model) {
            this.model = model;
            return this;
        }
        public Builder setProcessor(Processor processor) {
            this.processor = processor;
            return this;
        }
        public Builder setDisplay(Display display) {
            this.display = display;
            return this;
        }
        public Phone build(){
            return new Phone(id, model, processor, display);
        }

    }
}
