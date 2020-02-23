/**
 *
 * You can write your JS code here, DO NOT touch the default style file
 * because it will make it harder for you to update.
 * 
 */
$(function () {
   window.zero = {
       getLanguage: function () {
           return $("#lang_id").val();
       }, // end of getLanguage function

       loadI18nProps() {
           jQuery.i18n.properties({
              name: "js",
              path: "../../static/i18n/",
              mode: "map",
              language: this.getLanguage() || "zh",
              async: false,
              callback: function () {
                  // empty
              }
           });
       },

       getI18nProp(key) {
           return $.i18n.prop(key);
       },

       dateTimePicker(element, settings) {
           var defaultSettings = {
               format: 'YYYY-MM-DD HH:mm:ss',
               focusOnShow: true,
               buttons: {
                   showToday: true,
                   showClear: true,
                   showClose: true
               }
           };

           settings = $.extend(defaultSettings, settings);
           $(element).datetimepicker(settings);
       },

       dataTable: function (element, settings) {
           var defaultSettings = {
               "destroy": true,
               "autoWidth": false,
               "lengthMenu": [10, 20, 50],
               "searching": false,
               "lengthChange": true,
               "paging": true,
               "serverSide": true,
               "processing": true,
               "deferRender": true,
               "displayStart": 0,
               "pageLength": 10,
               "ordering": false,
               "dom": '<"top"B>r<"float-right"l>t<"bottom"ip><"clear">',
               "language": {
                   "emptyTable": "没有找到任何数据",
                   "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                   "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
                   "lengthMenu": "显示_MENU_条",
                   "loadingRecords": "数据加载中...",
                   "loading": "加载中...",
                   "processing": "处理中...",
                   "search": "搜索：",
                   "zeroRecords": "未找到匹配的数据",
                   "paginate": {
                       "first": "首页",
                       "last": "尾页",
                       "next": "下一页",
                       "previous": "上一页"
                   }
               },
               "buttons": [
                   {
                       "text": this.getI18nProp("refresh"),
                       "attr": {
                           "class": "btn btn-sm btn-outline-info ion-android-refresh",
                       },
                       "action": function(e, dt, node, config) {
                           dt.ajax.reload();
                       }
                   }
               ]
           };

           settings = $.extend(defaultSettings, settings);
           return $(element).DataTable(settings);
       },

       activeButtonAndSetText: function (element, text) {
           $(element).attr("disabled", false);
           $(element).html(text);
       }
   }
});

