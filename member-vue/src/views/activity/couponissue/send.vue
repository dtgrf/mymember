<template>
    <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" title="发送优惠券" width="900px">
      <el-form ref="form" :model="form" :rules="rules" :inline="true" size="small" label-width="140px">
        <el-card class="box-card" >
            <el-tag class="tag"
              v-for="tag in tags"
             :key="tag.uid"
              closable
              @close='handleClose(tag)'>
              {{tag.username}}
            </el-tag>
        </el-card>
        <el-table v-loading="loading" :data="data" size="small"  style="width: 100%;">
        <el-table-column prop="uid" label="用户id" width="90" sortable :sort-orders="['descending','ascending']" />
        <el-table-column prop="nickname" label="昵称" width="150"/>
        <el-table-column prop="username" label="用户名"  width="150"/>
        <el-table-column ref="table" prop="avatar" label="用户头像" width="100">
          <template slot-scope="scope">
            <a :href="avatarFormat(scope.row.avatar)" style="color: #42b983" target="_blank"><img :src="avatarFormat(scope.row.avatar)" alt="点击打开" class="el-avatar"></a>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号码" width="100"/>
        
        <el-table-column prop="integral" label="用户积分" width="80" />
        
        <el-table-column prop="payCount" label="购买次数" width="80"/>
  
        <el-table-column align="center" label="操作">
            <template slot-scope="scope" >
                  <el-row>
                      <el-button type="primary" @click="addCouponToUserList(scope.row)">添加</el-button>
                  </el-row>
            </template>
   
        </el-table-column>
  
      </el-table>
      <!--分页组件-->
      <el-pagination
        :total="total"
        :current-page="page + 1"
        style="margin-top: 8px;"
        layout="total, prev, pager, next, sizes"
        @size-change="sizeChange"
        @current-change="pageChange"
      />
        
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="cancel">取消</el-button>
        <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
      </div>
    </el-dialog>
  </template>
  
  <script>
  import {addcoupon } from '@/api/yxStoreCouponIssue'
  
  
  import initData from '@/mixins/crud'
  export default {
  
    mixins: [initData],
    props: {
      isAdd: {
        type: Boolean,
        required: true
      },
      id: {
        type: Number,
        required:true
      },
    },
    created() {
      this.$nextTick(() => {
        this.init()
      })
    },
    data() {
      return {
        loading: false, dialog: false,
        form: {
         },
        rules: {
        },
        uids:[],
        tags:[],
        couponid:this.id,
        myisadd:this.isAdd
      }
    },
    watch: {
      id: function (newVal) {
        this.couponid=newVal;
      },
    },
    methods: {
     
      doSubmit() {
        this.postCouponUserList()
      },
      doAdd() {
        add(this.form).then(res => {
          this.resetForms()
          this.$notify({
            title: '添加成功',
            type: 'success',
            duration: 2500
          })
          this.loading = false
          this.$parent.init()
        }).catch(err => {
          this.loading = false
          console.log(err.response.data.message)
        })
      },
      doEdit() {
        
      },
      resetForms() {
        this.dialog = false
        this.$refs['form'].resetFields()
        this.form = {
         
        }
      },
      beforeInit() {
        this.url = 'api/yxUser'
        const sort = 'uid,desc'
        this.params = { page: this.page, size: this.size, sort: sort, userType: this.userType }
        const query = this.query
        const type = query.type
        const value = query.value
        if (type && value) { this.params[type] = value }
        return true
      },
      avatarFormat(row){
        return process.env.VUE_APP_BASE_API+'/avatar/'+row
      },
      addCouponToUserList(row){
        if(this.tags.length>0)
        {
          let obj=this.tags.filter(t=>t.uid==row.uid)
          if(obj.length==0){
            this.tags.push(row)
          }
          else
          this.$message({message:"不能重复选",type:'info'}) 
        }
        else{
          this.tags.push(row)
        }
       
      },
      postCouponUserList(){
        this.form.userlist=this.tags;
        this.form.id=this.couponid
        addcoupon(this.form).then(res => {
          this.resetForms()
          this.$notify({
            title: '操作成功',
            type: 'success',
            duration: 2500
          })
          this.loading = false
          this.$parent.init()
        }).catch(err => {

          this.loading = false
          this.$message({
          message: '该用户已经发送过，不得再次发送',
          type: 'warning'
            });
          console.log(err.response.data.message)
        })
      },
      handleClose(row){
      let arr=this.tags.filter(t=>t.uid!=row.uid)
      this.tags=arr
      },
      cancel() {
        this.resetForms()
      },
    }
  }
  </script>
  
  <style scoped>
  .box-card{
    height:100px;
    overflow:auto;
  }
  .tag{
    margin: 10px;
  }
  </style>
  