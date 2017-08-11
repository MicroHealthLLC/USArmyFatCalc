package microhealthllc.com.usarmyfatcalc.DB;

/**
 * Created by ubuntuadmin on 3/13/17.
 */

public class BmiLogs {
    private int id;
    private String bodyfat;
    private String waist;
    private String datetime;
    private String neck;
    private String hieght;

    public BmiLogs() {
    }

    public BmiLogs(String bodyfat,String waist,String neck,String height, String datetime)
    {
        this.id=id;
        this.bodyfat =bodyfat;
        this.waist =waist;
        this.hieght = height;
        this.datetime =datetime;
        this.neck = neck;
    }

    public BmiLogs(int id,String bodyfat,String waist, String neck,String height, String datetime)
    {
        this.id = id;
        this.bodyfat =bodyfat;
        this.waist =waist;
        this.neck = neck;
        this.hieght =height;
        this.datetime =datetime;

    }
    public void setId(int id) {
        this.id = id;
    }
    public void setBodyfat(String bodyfat) {
        this.bodyfat = bodyfat;
    }
    public void setNeck(String neck){
        this.neck = neck;
    }

    public void setHieght(String hieght) {
        this.hieght = hieght;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }
    public void setDateTime(String datetime){
        this.datetime = datetime;
    }
    public int getId() {
        return id;
    }
    public String getBodyfat() {
        return bodyfat;
    }
    public String getWaist() {
        return waist;
    }
    public String getNeck(){
        return this.neck;
    }
    public String getHeight(){
        return this.hieght;
    }
    public String getDateTime() {
       return datetime;
    }
}

