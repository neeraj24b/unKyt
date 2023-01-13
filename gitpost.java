/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.io.InputStreamReader;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.X509TrustManager;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class gitpost {

    private final static String url = "https://untrusted-root.badssl.com/";

    public static void main(String[] args) {
        gitpost git = new gitpost();
        try {
            git.call();
        } catch (Exception ex) {
            Logger.getLogger(gitpost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void call() {
        TrustManager[] TrustMgr = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        try {

            try {

                String urlParameters = "user=user&pass=pass";
                URL ur = new URL(url + "");
                byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                int postDataLength = postData.length;
                SSLContext SSLCont = SSLContext.getInstance("SSL");
                SSLCont.init(null, TrustMgr, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(SSLCont.getSocketFactory());

                HostnameVerifier ValidHost = new HostnameVerifier() {
                    @Override
                    public boolean verify(String HostName, SSLSession MySession) {
                        return true;
                    }
                };

                // Installing an all-trusting verifier for the HOST
                HttpsURLConnection.setDefaultHostnameVerifier(ValidHost);
                HttpsURLConnection conn = (HttpsURLConnection) ur.openConnection();
                conn.setDoOutput(true);
                conn.setInstanceFollowRedirects(false);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                conn.setUseCaches(false);
                try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                    wr.write(postData);
                    InputStream responseInputStr = conn.getInputStream();
                    String tokenResponse = null;
                    if (responseInputStr != null) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(responseInputStr));
                        String output;
                        StringBuffer responseStrBuf = new StringBuffer();
                        while ((output = br.readLine()) != null) {
                            responseStrBuf.append(output);
                        }
                        br.close();
                        tokenResponse = responseStrBuf.toString();
                    }
                    ;
                    System.out.println("" + tokenResponse);
                }

            } catch (IOException | KeyManagementException | NoSuchAlgorithmException e) {
            }

        } catch (Exception e) {
        }
    }
}
