���Ϲ���java����pdf������, �漰����Ĵ��붼��Ӳ����`C://windows/fonts/x.ttf`. Ϊ��, �ṩ���springmvc������. 

���õĹؼ���:

```xml
<bean id="pdfWriter" class="com.can.pdf.PdfWriter">
    <property name="pdfRendererBuilder" ref="pdfRendererBuilder" />
    <property name="beetlConfig" ref="beetlConfig" />
    <property name="fontMap">
        <map>
            <!-- key����Ϊģ���ļ����font-family���� -->
            <entry key="simkai" value="/WEB-INF/font/simkai.ttf"/>
        </map>
    </property>
</bean>
```