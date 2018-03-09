package com.infusionsoft;

import com.infusionsoft.api.UserInfoApi;
import com.infusionsoft.model.InfusionsoftUserInfoDTO;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        try {
            UserInfoApi userInfoApi = new UserInfoApi();
            setupApiClient(userInfoApi, "--users-access-token--");

            InfusionsoftUserInfoDTO result = userInfoApi.getUserInfoUsingGET();

            System.out.println(result);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void setupApiClient(Object apiResource, String accessToken){
        ApiClient apiClient = new ApiClient();
        apiClient.addDefaultHeader("Host","api.infusionsoft.com");
        apiClient.addDefaultHeader("Authorization","Bearer " + accessToken);

        try {
            apiResource.getClass().getMethod("setApiClient", ApiClient.class).invoke(apiResource,apiClient);
        } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException ex) {
            System.out.println("Unable to configure ApiClient for "+ apiResource.getClass().getName());
        }
    }
}
