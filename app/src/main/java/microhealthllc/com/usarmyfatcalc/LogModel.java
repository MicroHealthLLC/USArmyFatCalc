package microhealthllc.com.usarmyfatcalc;

/**
 * Created by dan on 3/20/17.
 */


public class LogModel {
    private String bodyfat, waist, height, neck, datetime;

    public LogModel() {
    }


    public LogModel(String bodyfat,String waist, String neck,String height, String datetime) {

        this.bodyfat = bodyfat;
        this.waist = waist;
        this.neck = neck;
        this.height = height;
        this.datetime = datetime;
    }

    public void setBodyfat(String bodyfat) {
        this.bodyfat = bodyfat;
    }
    public void setNeck(String neck){
        this.neck = neck;
    }

    public void setHieght(String hieght) {
        this.height = hieght;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }
    public void setDateTime(String datetime){
        this.datetime = datetime;
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
        return this.height;
    }
    public String getDateTime() {
        return datetime;
    }
}