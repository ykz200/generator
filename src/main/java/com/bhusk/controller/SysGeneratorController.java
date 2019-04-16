package com.bhusk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bhusk.config.DBIdentifier;
import com.bhusk.config.ProjectDBMgr;
import com.bhusk.entity.DataBase;
import com.bhusk.service.SysGeneratorService;
import com.bhusk.utils.CookiesUtils;
import com.bhusk.utils.PageUtils;
import com.bhusk.utils.Query;
import com.bhusk.utils.R;
import com.bhusk.utils.StatusBean;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午9:12:58
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpSession session) throws IOException {
        //  ProjectDBMgr.add("112.74.54.188","institute");
        DataBase dataBase = (DataBase) request.getSession().getAttribute("dataBase");
        if (null != dataBase && dataBase.getDb() != null && dataBase.getUrl() != null && dataBase.getPort() != null && dataBase.getUsername() != null && dataBase.getPassword() != null) {
            ProjectDBMgr.add(dataBase);
            DBIdentifier.setProjectCode(ProjectDBMgr.DB_KEY);
            //查询列表数据
            Query query = new Query(params);
            List<Map<String, Object>> list = sysGeneratorService.queryList(query);
            //分页查总数量
            int total = sysGeneratorService.queryTotal(query);
            PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
            return R.ok().put("page", pageUtil);

        }
        return R.error("无数据库连接");
    }

    /**
     * 生成代码
     */
    @RequestMapping("/code")
    public void code(String tables, HttpServletResponse response, HttpServletRequest request) throws IOException {
        DataBase dataBase = (DataBase) request.getSession().getAttribute("dataBase");
        ProjectDBMgr.add(dataBase);
        DBIdentifier.setProjectCode(ProjectDBMgr.DB_KEY);
        byte[] data = sysGeneratorService.generatorCode(tables.split(","));

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"project.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

    @RequestMapping("/session")
    @ResponseBody
    public StatusBean getSession(HttpSession session, DataBase dataBase, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        try {

            session.setAttribute("dataBase", dataBase);
            List<DataBase> list = new ArrayList<DataBase>();
            //获取数据源列表
            Cookie dblist = CookiesUtils.getCookieByName(request, "jsonStr");
            if (null != dblist) {
                String dblistStr = java.net.URLDecoder.decode(dblist.getValue(), "utf-8");
                // 解析多个对象成list集合 使用JSONArray数组
                list.addAll(new ArrayList<DataBase>(JSONArray.parseArray(dblistStr, DataBase.class)));

                /**
                 * 防止重复保存db数据库连接
                 */
                if (null != list) {
                    for (int i = 0; i < list.size(); i++) {
                        if (null != list.get(i) && dataBase.equals(list.get(i))) {
                            list.remove(i);
                            i--;
                        }
                    }
                }
                list.add(dataBase);
            }

            //创建json集合
            String jsonStr = JSON.toJSONString(list);
            jsonStr = URLEncoder.encode(jsonStr, "UTF-8");

            Cookie cookie = new Cookie("url", dataBase.getUrl());
            Cookie cookie1 = new Cookie("db", dataBase.getDb());
            Cookie cookie2 = new Cookie("port", dataBase.getPort());
            Cookie cookie3 = new Cookie("username", dataBase.getUsername());
            Cookie cookie4 = new Cookie("password", dataBase.getPassword());
            Cookie cookie5 = new Cookie("jsonStr", jsonStr);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 30);
            cookie1.setPath("/");
            cookie1.setMaxAge(60 * 60 * 24 * 30);
            cookie2.setPath("/");
            cookie2.setMaxAge(60 * 60 * 24 * 30);
            cookie3.setPath("/");
            cookie3.setMaxAge(60 * 60 * 24 * 30);
            cookie4.setPath("/");
            cookie4.setMaxAge(60 * 60 * 24 * 30);
            cookie5.setPath("/");
            cookie5.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
            response.addCookie(cookie4);
            response.addCookie(cookie5);

            if (dataBase != null) {
                return new StatusBean(true, "有数据连接");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new StatusBean(false, "无数据连接");
    }

}