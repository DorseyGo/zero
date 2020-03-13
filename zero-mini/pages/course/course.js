// pages/course/course.js
const app = getApp();
const request = require("../../utils/request")

//Component Object
Component({
  data: {
    notifications: [],
    indicatorDots: true,
    autoplay: true,
    duration: 500,
    interval: 500,
    courses: [],
    curCategory: 2,
    courseCategories: []
  },
  methods: {
    activateCurCategory() {
      this.data.courseCategories.forEach(elem => {
        if (elem.active === 0) {
          this.setData({
            curCategory: elem.id
          })
        }
      })
    }
  },
  
  // when rendered in its parent container
  attached: function(){
    request.get('/course/categories').then(res => {
      this.setData({
        courseCategories: res.data
      })

      this.activateCurCategory()
      return request.get(`/courses/categories/${this.data.curCategory}`);
    }).then(res => {
      this.setData({
        courses: res.data
      })
    }).catch(err => {
      console.log(err);
    })

    // load notifications
    request.get('/notifications').then(res => {
      this.setData({
        notifications: res.data
      })
    }).catch(err => {
      console.log(err);
    })
  }
});