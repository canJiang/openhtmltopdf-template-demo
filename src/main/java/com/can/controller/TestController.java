package com.can.controller;

import com.can.pdf.PdfWriter;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private PdfWriter pdfWriter;

    @RequestMapping("testpdf")
    @ResponseBody
    public String testPdf() {
        Map<String, Object> rowDataMap = new HashMap<>();
        rowDataMap.put("billTypeStr", "支付停车费");
        rowDataMap.put("parkName", "天安门停车场");
        rowDataMap.put("carNo", "粤E88888");
        rowDataMap.put("payTime", new Date());
        rowDataMap.put("billValue", new BigDecimal(5));

        List<Map<String, Object>> dataList = new LinkedList<>();
        dataList.add(rowDataMap);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("dataList", dataList);
        String html = pdfWriter.generateHtml("comsumeList.tpl", dataMap);
        if (html.isEmpty()) {
            logger.error("generateHtml error");
            return "generateHtml error";
        }

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File("D:/test.pdf"));
        } catch (FileNotFoundException e) {
            logger.error("OutputStream error", e);
            return "OutputStream error";
        }

        outputStream = pdfWriter.writePdf(html, outputStream);
        if (outputStream == null) {
            logger.error("writePdf error");
            return "writePdf error";
        }

        IOUtils.closeQuietly(outputStream);
        return "ok";
    }
}
