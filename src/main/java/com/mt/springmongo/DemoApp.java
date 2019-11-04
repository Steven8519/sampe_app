package com.mt.springmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;

/**
 * Main Application controller.
 *
 * @author Mithun 
 */
@Controller
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.mt.*")
public class DemoApp {

    public static void main(String[] args) throws IOException, ApiException {
        SpringApplication.run(DemoApp.class, args);

        ApiClient client = Config.fromConfig("config");
        client.setVerifyingSsl(false);
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();

        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        for (V1Pod item : list.getItems()) {
            System.out.println(item.getMetadata().getName());
        }
    }


    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
}
