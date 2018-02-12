package recyclerview.mommoo.com.recyclerview;

/**
 * Created by yonghak kang on 2018-02-11.
 */
public enum RangeType {
    DAY(0),WEEK(1),MONTH(2),QUARTER(3),YEAR(4);

    int id;
    RangeType(int id){
        this.id = id;
    }

    static RangeType fromId(int id){
        for(RangeType type : values()){
            if(type.id == id)return type;
        }
        throw new IllegalArgumentException();
    }
}
