package cn.az.boot.management.mbean;

/**
 * @author az
 */
public interface SimpleDataMBean {


    /**
     * Setter
     * Property
     * 属性
     *
     * @param data
     */
    void setData(String data);

    /**
     * Getter
     * 属性
     *
     * @return
     */
    String getData();


    /**
     * 展示数据
     * 操作(Operation)
     *
     * @return
     */
    String displayData();
}
