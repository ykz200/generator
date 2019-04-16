$(function () {
    $("#jqGrid").jqGrid({
        url: 'sys/generator/list',
        datatype: "json",

        colModel: [

            {label: '表名', name: 'tableName', width: 100, key: true},
            {label: 'Engine', name: 'engine', width: 70},
            {label: '表备注', name: 'tableComment', width: 100},
            {label: '创建时间', name: 'createTime', width: 100}
        ],

        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50, 100, 200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {

            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {

            var rowNum = $("#jqGrid").jqGrid('getGridParam', 'records');
            if (rowNum == 0) {
                window.alert(" 无数据库连接，请重新连接");
                //   layer.msg('无数据库连接', {icon: 2});
                location.href = "../main.html";
            }

            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }

    });
    // var re_records = $("#jqGrid").jqGrid('getGridParam', 'records'); //获取数据总条数
    // if (re_records == 0) {
    //
    //     // layer.msg('没有可以保存的信息', {icon: 2});
    //
    //     // window.location.href = "../main.html";
    // }
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            tableName: null
        }
    },
    methods: {
        query: function () {
            $("#jqGrid").jqGrid('setGridParam', {

                postData: {'tableName': vm.q.tableName},
                page: 1
            }).trigger("reloadGrid");
        },

        generator: function () {
            var tableNames = getSelectedRows();
            if (tableNames == null) {
                return;
            }
            location.href = "sys/generator/code?tables=" + tableNames.join();
        }
    }
});





