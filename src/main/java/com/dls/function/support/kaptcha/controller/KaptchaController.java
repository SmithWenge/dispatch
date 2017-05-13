package com.dls.function.support.kaptcha.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KaptchaController {
    private static final String RESPONSE_DATE_HEADER_KEY = "Expires";
    private static final String RESPONSE_HEADER_CACHE_CONTROL_KEY = "Cache-Control";
    private static final String RESPONSE_HEADER_CACHE_CONTROL_VALUE_1 = "no-store, no-cache, must-revalidate";
    private static final String RESPONSE_HEADER_CACHE_CONTROL_VALUE_2 = "post-check=0, pre-check=0";
    private static final String RESPONSE_HEADER_PRAGMA_KEY = "Pragma";
    private static final String RESPONSE_HEADER_PRAGMA_VALUE = "no-cache";
    private static final String RESPONSE_CONTENT_TYPE_MIME = "image/jpeg";
    private static final String IO_WRITE_IMAGE_FORMAT = "jpg";

    @Autowired
    private Producer captchaProducer = null;

    public Producer getCaptchaProducer() {
        return captchaProducer;
    }

    @Autowired
    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    @Autowired
    public KaptchaController(@Qualifier("captchaProducer") Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    /**
     * 向某个页面返画出来的新的注册码图片
     *
     * @throws IOException
     */
    @RequestMapping("/captchaImage")
    public void createKaptchaImage(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        // Set to expire far in the past.
        response.setDateHeader(RESPONSE_DATE_HEADER_KEY, 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader(RESPONSE_HEADER_CACHE_CONTROL_KEY, RESPONSE_HEADER_CACHE_CONTROL_VALUE_1);
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader(RESPONSE_HEADER_CACHE_CONTROL_KEY, RESPONSE_HEADER_CACHE_CONTROL_VALUE_2);
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader(RESPONSE_HEADER_PRAGMA_KEY, RESPONSE_HEADER_PRAGMA_VALUE);

        // return a jpeg
        response.setContentType(RESPONSE_CONTENT_TYPE_MIME);

        // create the text for the image
        String capText = captchaProducer.createText();

        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bufferedImage = captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bufferedImage, IO_WRITE_IMAGE_FORMAT, out);

        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping("/captchaCheck")
    public void captchaCheckValue(HttpServletRequest request) {
        String kaptchaExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String kaptchaReceived = request.getParameter("captchaCode");

        if (Strings.isNullOrEmpty(kaptchaReceived) || kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {
            System.out.println("GOOD! The kaptcha is right");
        }
    }
}
