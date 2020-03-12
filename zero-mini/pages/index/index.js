//index.js
//获取应用实例
const app = getApp()
const request = require("../../utils/request");

Page({
  data: {
    curPage: 'course',
    tabs: []
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },

  activateCurPage: function () {
    this.data.tabs.forEach(elem => {
      if (elem.active === 0) {
        this.setData({
          curPage: elem.component
        })
      }
    });
  },

  onLoad: function () {
    request.get('/tabs').then((res) => {
      this.setData({
        tabs: res.data
      })

      this.activateCurPage()
    }).catch(err => {
      
    })
  },

  navTo: function (e) {
    let curPage = e.currentTarget.dataset.cur
    this.setData({curPage})
  }
})
