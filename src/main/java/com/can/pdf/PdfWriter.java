package com.can.pdf;

import com.openhtmltopdf.extend.FSSupplier;
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import org.beetl.core.Template;
import org.beetl.core.exception.BeetlException;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletContext;

public class PdfWriter implements ServletContextAware {
    private Logger logger = LoggerFactory.getLogger(PdfWriter.class);

    private Map<String, String> fontMap;

    private PdfRendererBuilder pdfRendererBuilder;

    private BeetlGroupUtilConfiguration beetlConfig;

    /**
     * 把html内容保存为pdf
     * @param htmlContent  html内容
     * @param outputStream
     * @return
     */
    public OutputStream writePdf(String htmlContent, OutputStream outputStream) {
        pdfRendererBuilder.withHtmlContent(htmlContent, "./")
                .toStream(outputStream);

        try {
            pdfRendererBuilder.run();
        } catch (Exception e) {
            logger.error("writePdf error", e);
            return null;
        }

        return outputStream;
    }

    /**
     * 将指定模板文件转换为html内容
     * @param templateFileName 模板文件名
     * @param dataMap 填充模板的数据
     * @return html内容
     */
    public String generateHtml(String templateFileName, Map<String, Object> dataMap) {
        Template template = beetlConfig.getGroupTemplate().getTemplate(templateFileName);
        template.binding(dataMap);

        try {
            return template.render();
        } catch (BeetlException e) {
            logger.error("generateHtml error", e);
            return "";
        }
    }

    /**
     * 配置读取字体文件
     * @param servletContext
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        if (fontMap != null) {
            for (Map.Entry<String, String> entry : fontMap.entrySet()) {
                pdfRendererBuilder.useFont(new FSSupplier<InputStream>() {
                    @Override
                    public InputStream supply() {
                        return servletContext.getResourceAsStream(entry.getValue());
                    }
                }, entry.getKey(), 400, BaseRendererBuilder.FontStyle.NORMAL, true);
            }
        }
    }

    public void setFontMap(Map<String, String> fontMap) {
        this.fontMap = fontMap;
    }

    public void setPdfRendererBuilder(PdfRendererBuilder pdfRendererBuilder) {
        this.pdfRendererBuilder = pdfRendererBuilder;
    }

    public void setBeetlConfig(BeetlGroupUtilConfiguration beetlConfig) {
        this.beetlConfig = beetlConfig;
    }
}


