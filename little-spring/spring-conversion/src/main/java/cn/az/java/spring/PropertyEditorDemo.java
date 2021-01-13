package cn.az.java.spring;

import java.beans.PropertyEditor;

/**
 * @author az
 * @since 08/10/20
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {

        // 模拟 Spring Framework 操作
        // 有一段文本 name = 小马哥;
        String text = "name = 小马哥";

        PropertyEditor propertyEditor = new String2PropertiesPropertyEditor();
        // 传递 String 类型的内容
        propertyEditor.setAsText(text);

        System.out.println(propertyEditor.getValue());

        System.out.println(propertyEditor.getAsText());
    }
}
