网上关于java导出pdf的例子, 涉及字体的代码都是硬编码`C://windows/fonts/x.ttf`. 为此, 提供结合springmvc的例子. 

配置的关键点:

```xml
<bean id="pdfWriter" class="com.can.pdf.PdfWriter">
    <property name="pdfRendererBuilder" ref="pdfRendererBuilder" />
    <property name="beetlConfig" ref="beetlConfig" />
    <property name="fontMap">
        <map>
            <!-- key设置为模板文件里的font-family名称 -->
            <entry key="simkai" value="/WEB-INF/font/simkai.ttf"/>
        </map>
    </property>
</bean>
```