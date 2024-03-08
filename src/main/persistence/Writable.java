package persistence;

import org.json.JSONObject;
//Pulled from JsonSerializationDemo-master

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
