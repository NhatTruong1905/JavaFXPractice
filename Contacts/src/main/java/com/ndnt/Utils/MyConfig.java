/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndnt.Utils;

import com.ndnt.services.GroupServices;
import com.ndnt.services.user.BaseUserServices;
import com.ndnt.services.user.UserServices;
import com.ndnt.services.user.UpdateUserService;

/**
 *
 * @author admin
 */
public class MyConfig {

    public static final GroupServices groupServices = new GroupServices();
    public static final BaseUserServices userSerivces = new UserServices();
    public static final UpdateUserService updateServices = new UpdateUserService();

}
