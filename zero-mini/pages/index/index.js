//index.js
//获取应用实例
const app = getApp()
const request = require("../../utils/request");

Page({
  data: {
    tabs: []
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    request.get('/tabs').then((res) => {
      this.setData({
        tabs: res.data
      })
    }).catch(err => {
      
    })

    console.log('data: ' + this.data.tabs)
  }
})
