package ua.nure.dao.entetity;

import org.bson.types.ObjectId;

public class Phone {
    private int id;
    private String model;
    private Processor processor;
    private Display display;
    private ObjectId objId;

    @Override
    public String toString() {
        return "Phone{" +
                "objId=" + objId +
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
    private Phone(int id, String model, Processor processor, Display display, ObjectId objId) {
        this.id = id;
        this.model = model;
        this.processor = processor;
        this.display = display;
        this.objId = objId;
    }
    private Phone(String model, Processor processor, Display display) {
        this.model = model;
        this.processor = processor;
        this.display = display;
    }
    private Phone(String model, Processor processor, Display display, ObjectId objId) {
        this.model = model;
        this.processor = processor;
        this.display = display;
        this.objId = objId;
    }

    public int getId() {
        return id;
    }
    public ObjectId getObjId() {
        return this.objId;
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
        private ObjectId objId;

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
        public Builder setObjId(ObjectId objId) {
            this.objId = objId;
            return this;
        }
        public Phone build(){
            return new Phone(id, model, processor, display, objId);
        }

    }
}
