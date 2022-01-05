package com.boleteria.util;

import com.boleteria.model.ResponseCaptcha;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class VerifyRecaptcha {
    private static final Logger LOGGER = LogManager.getLogger(VerifyRecaptcha.class);

    public static final String url = "https://www.google.com/recaptcha/api/siteverify";
    public static final String secret = "6LfWBu8dAAAAALNutyGh-HBFNfj_xx5ZPPouw8Nm";
    private final static String USER_AGENT = "Mozilla/5.0";

    public static boolean verify(String gRecaptchaResponse) throws IOException {
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }
        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            // add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "es-EC,en;q=0.5");
            String postParams = "secret=" + secret + "&response=" + gRecaptchaResponse;
            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            if (con.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //parse JSON response and return 'success' value
                Gson gson = new Gson();
                ResponseCaptcha responseCaptcha = gson.fromJson(response.toString().replace(" ", ""), ResponseCaptcha.class);
                return responseCaptcha.isSuccess() && responseCaptcha.getScore() >= 0.7;
            } else {
                return false;
            }
        } catch(Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }
}