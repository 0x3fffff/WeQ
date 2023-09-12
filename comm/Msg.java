package comm;

import java.io.Serializable;

public class Msg implements Serializable {
    int type; //0.信息   -1.版本    1.人数
    Object obj;

    public Msg(int type, Object obj) {
        this.type = type;
        this.obj = obj;
    }

    public int getType() {
        return type;
    }

    public Object getObj() {
        return obj;
    }
}
