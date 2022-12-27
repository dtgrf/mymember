<template>
    <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" :title="isAddForm ? '新增' : '编辑'" width="500px">
      <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="form.nickname" :disabled="isAddForm ? true : false" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="真实姓名" prop="realName">
          <el-input v-model="form.realName" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="用户备注">
          <el-input v-model="form.mark" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="email" prop="email">
          <el-input v-model="form.email" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="用户积分">
          <el-input v-model="form.integral" style="width: 370px;" />
        </el-form-item>
        <el-form-item label="头像">
          <el-input v-model="form.avatar" style="width: 500px;" v-show="false"/>
          <el-upload
            class="avatar-uploader"
            :headers="headers"
            :action="preUpdateAvatar"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            >
            <img v-if="form.avatar" :src="form.avatar ? baseApi + '/avatar/' + form.avatar : '404.png'" class="avatar"/>
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="预留1">
          <el-radio v-model="form.isPromoter" :label="1">开启</el-radio>
          <el-radio v-model="form.isPromoter" :label="0">关闭</el-radio>
        </el-form-item>
        <el-form-item label="预留2">
          <el-radio v-model="form.adminid" :label="1">开启</el-radio>
          <el-radio v-model="form.adminid" :label="0">关闭</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="text" @click="cancel">取消</el-button>
        <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
      </div>
    </el-dialog>
  </template>
  
  <script>
  import { add, edit } from '@/api/yxUser'
  import picUpload from '@/components/pic-upload'
  import { getToken } from '@/utils/auth'
  import { mapGetters } from 'vuex'
  export default {
    components: {  picUpload },
    props: {
      isAddForm: {
        type: Boolean,
        required: true
      },

    },
    data() {
      return {
        loading: false, dialog: false,
        form: {
          uid: '',
          account: '',
          pwd: '',
          realName: '',
          birthday: '',
          cardId: '',
          mark: '',
          partnerId: '',
          groupId: '',
          nickname: '',
          avatar: '',
          avatarId:'',
          phone: '',
          addTime: '',
          addIp: '',
          lastTime: '',
          lastIp: '',
          nowMoney: '',
          brokeragePrice: '',
          integral: '',
          signNum: '',
          status: '',
          level: '',
          spreadUid: '',
          spreadTime: '',
          userType: '',
          isPromoter: 0,
          payCount: '',
          spreadCount: '',
          cleanTime: '',
          addres: '',
          adminid: 0,
          loginType: '',
          email:''
        },
        rules: {
            email: [
           { required: true, message: 'email不能为空~', trigger: 'blur' }
           ],
           nickname:[
            { required: true, message: '昵称不能为空~', trigger: 'blur'}
            ],
            phone: [
          { required: true, message: '手机不能为空~', trigger: 'blur' }
            ],
            username: [
          { required: true, message: '用户名不能为空~', trigger: 'blur' }
            ],
        },
        imageUrl:'',
        headers: {
          'Authorization': getToken()
        },
        
      }
    },
    watch:{
        form(){
            console.log('form 发生了变化');
        }
    },
    computed:{
    ...mapGetters([
      'preUpdateAvatar',
      'user',
      'baseApi'
    ])
    },
    methods: {
      cancel() {
        this.resetForm()
      },
      doSubmit() {
        this.loading = true
        if (this.Form) {
          this.doAdd()
        } else this.doEdit()
      },
      doAdd() {
        add(this.form).then(res => {
          this.resetForm()
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
        edit(this.form).then(res => {
          this.resetForm()
          this.$notify({
            title: '修改成功',
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
      resetForm() {
        this.dialog = false
        this.$refs['form'].resetFields()
        this.form = {
          uid: '',
          account: '',
          pwd: '',
          realName: '',
          birthday: '',
          cardId: '',
          mark: '',
          partnerId: '',
          groupId: '',
          nickname: '',
          avatar: '',
          avatarId:'',
          phone: '',
          addTime: '',
          addIp: '',
          lastTime: '',
          lastIp: '',
          nowMoney: '',
          brokeragePrice: '',
          integral: '',
          signNum: '',
          status: '',
          level: '',
          spreadUid: '',
          spreadTime: '',
          userType: '',
          isPromoter: '',
          payCount: '',
          spreadCount: '',
          cleanTime: '',
          addres: '',
          adminid: '',
          loginType: '',
          email:''
        }
      },
      handleAvatarSuccess(res, file) {

        this.form.avatarId=res.split(",")[0];
        let a=res.split(",")[1];
        let arr=a.split('\\');
        let filename=arr[arr.length-1]
        this.imageUrl = URL.createObjectURL(file.raw);
        this.form.avatar=filename
        this.$forceUpdate()
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg'||file.type === 'image/png'||file.type === 'image/gif';
        const isLt2M = file.size / 1024 / 1024 < 2;
        file.test="ssss"
        if (!isJPG) {
          this.$message.error('上传头像图片只能是图片格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    }
  }
  </script>
  
  <style>
   .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  </style>
  