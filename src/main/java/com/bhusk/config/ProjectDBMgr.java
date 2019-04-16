package com.bhusk.config;

import com.bhusk.entity.DataBase;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目数据库管理。提供根据项目编码查询数据库名称,IP的接口,端口号。
 *
 * @author elon
 * @version 2018年2月25日
 */
public class ProjectDBMgr {
    /**
     * 数据库动态默认key
     */
    public final static String DB_KEY = "project_connect";
    /**
     * 保存项目编码与数据名称的映射关系。这里是硬编码，实际开发中这个关系数据可以保存到redis缓存中；
     * 新增一个项目或者删除一个项目只需要更新缓存。到时这个类的接口只需要修改为从缓存拿数据。
     */
    private static Map<String, String> dbNameMap = new HashMap<String, String>();
    /**
     * 保存项目编码与数据库IP的映射关系。
     */
    private static Map<String, String> dbIPMap = new HashMap<String, String>();
    /**
     * 保存项目编码与数据库端口的映射关系。
     */
    private static Map<String, String> dbPRMap = new HashMap<String, String>();
    /**
     * 保存项目编码与数据库用户名的映射关系。
     */
    public static Map<String, String> dbUSMap = new HashMap<String, String>();
    /**
     * 保存项目编码与数据库密码的映射关系。
     */
    public static Map<String, String> dbPAMap = new HashMap<String, String>();

    private ProjectDBMgr() {
        dbNameMap.put("project_001", "institute");
        dbNameMap.put("project_002", "institute");
        dbNameMap.put("project_003", "institute");

        dbIPMap.put("project_001", "112.74.54.188");
        dbIPMap.put("project_002", "112.74.54.188");
        dbIPMap.put("project_003", "112.74.54.188");

        dbPRMap.put("project_001", "3306");
        dbPRMap.put("project_002", "3306");
        dbPRMap.put("project_003", "3306");

        dbUSMap.put("project_001", "root");
        dbUSMap.put("project_002", "root");
        dbUSMap.put("project_003", "root");

        dbPAMap.put("project_001", "Aa123456");
        dbPAMap.put("project_002", "Aa123456");
        dbPAMap.put("project_003", "Aa123456");


    }

    public static void add(DataBase dataBase) {
        ProjectDBMgr.dbNameMap.put(ProjectDBMgr.DB_KEY, dataBase.getDb());
        ProjectDBMgr.dbIPMap.put(ProjectDBMgr.DB_KEY, dataBase.getUrl());
        ProjectDBMgr.dbPRMap.put(ProjectDBMgr.DB_KEY, dataBase.getPort());
        ProjectDBMgr.dbUSMap.put(ProjectDBMgr.DB_KEY, dataBase.getUsername());
        ProjectDBMgr.dbPAMap.put(ProjectDBMgr.DB_KEY, dataBase.getPassword());
    }

    public static ProjectDBMgr instance() {
        return ProjectDBMgrBuilder.instance;
    }

    // 实际开发中改为从缓存获取
    public String getDBName(String projectCode) {
        if (dbNameMap.containsKey(projectCode)) {
            return dbNameMap.get(projectCode);
        }

        return "";
    }

    //实际开发中改为从缓存中获取
    public String getDBIP(String projectCode) {
        if (dbIPMap.containsKey(projectCode)) {
            return dbIPMap.get(projectCode);
        }
        return "";
    }

    //实际开发中改为从缓存中获取
    public String getDBPR(String projectCode) {
        if (dbPRMap.containsKey(projectCode)) {
            return dbPRMap.get(projectCode);
        }
        return "";
    }

    //实际开发中改为从缓存中获取
    public String getDBUS(String projectCode) {
        if (dbUSMap.containsKey(projectCode)) {
            return dbUSMap.get(projectCode);
        }
        return "";
    }

    //实际开发中改为从缓存中获取
    public String getDBPA(String projectCode) {
        if (dbPAMap.containsKey(projectCode)) {
            return dbPAMap.get(projectCode);
        }
        return "";
    }


    private static class ProjectDBMgrBuilder {
        static ProjectDBMgr instance = new ProjectDBMgr();

    }
}

