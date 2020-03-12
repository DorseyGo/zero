// pages/course/course.js
const app = getApp();
const request = require("../../utils/request")

//Component Object
Component({
  data: {
    courseCategories: []
  },
  methods: {
    
  },
  created: function(){

  },
  
  // when rendered in its parent container
  attached: function(){
    request.get('/course/categories').then(res => {
      this.setData({
        courseCategories: res.data
      })
    }).catch(err => {
      console.log(err);
    })
  }
});