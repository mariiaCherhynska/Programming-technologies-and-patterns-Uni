package ua.nure.observer;


import ua.nure.dao.entetity.Display;
import ua.nure.dao.entetity.Processor;

public class ObserverImplementation implements Observer {
    private int id;
    private String model;
    private Processor processor;
    private Display display;
    private ObserverImplementation(int id, String model, Processor processor, Display display) {
        this.id = id;
        this.model = model;
        this.processor = processor;
        this.display = display;
    }
    private ObserverImplementation(String model, Processor processor, Display display) {
        this.model = model;
        this.processor = processor;
        this.display = display;
    }

    public ObserverImplementation() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
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
    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", processor=" + processor +
                ", display=" + display +
                '}';
    }
    @Override
    public void update(String m) {
        System.out.println("Message: " + m);
    }
    public static class Builder{
        private int idComputer;
        private String model;
        private Processor processor;
        private Display display;

        public Builder(){}
        public Builder setIdComputer(int idComputer) {
            this.idComputer = idComputer;
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
        public ObserverImplementation build(){
            return new ObserverImplementation(idComputer, model, processor, display);
        }
    }
}
