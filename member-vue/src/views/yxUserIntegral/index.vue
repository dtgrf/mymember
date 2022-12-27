<template>
    <div class="app-container">
        <!--工具栏-->
        <div class="head-container">
                <div v-if="crud.props.searchToggle">
                    <!-- 搜索 -->
                    <el-input v-model="query.value" clearable placeholder="输入搜索内容" style="width: 200px;"
                              class="filter-item" @keyup.enter.native="crud.toQuery"/>
                    <el-select v-model="query.type" clearable placeholder="类型" class="filter-item" style="width: 130px">
                        <el-option v-for="item in queryTypeOptions" :key="item.key" :label="item.display_name"
                                   :value="item.key"/>
                    </el-select>
                    <rrOperation :crud="crud"/>
                </div>
            <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
            <crudOperation :permission="permission"/>
            <!--表单组件-->
            <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0"
                       title="赠送积分" width="500px">  
                <el-form ref="form" :model="form" :rules="rules" size="small"
                         label-width="100px" >
                                <el-form-item v-show="false"
                                        label="记录表id" prop="id">
                                        <el-input v-model="form.id"  style="width: 370px;"/>
                                </el-form-item>
                                <el-form-item
                                        label="积分标题">
                                        <el-input v-model="form.title" style="width: 370px;"/>
                                </el-form-item>
                                <el-form-item
                                        label="积分数">
                                        <el-input v-model="form.integralNum" oninput="value=value.replace(/[^\d]/g,'')" style="width: 370px;"/>
                                </el-form-item>
                                <el-form-item
                                        label="备注">
                                        <el-input v-model="form.remark" style="width: 370px;"/>
                                </el-form-item>
                                <!-- <el-form-item
                                        label="赠与用户的id"  >
                                        <el-input v-model="form.userid" style="width: 200px;"/>
                                </el-form-item> -->
                                <el-form-item
                                        label="赠送用户">
                                        <el-select size="mini" v-model="form.userid" 
                                            default-first-option filterable @change="WOStatusChange"
                                            placeholder="请选择用户">
                                            <el-option 
                                                v-for="item in userArray" 
                                                :key="item.key" 
                                                :label="item.label"
                                                :value="item.value">
                                            </el-option>
                                        </el-select>
                                </el-form-item>
                                

                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="text" @click="crud.cancelCU">取消</el-button>
                    <el-button :loading="crud.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
                </div>
            </el-dialog>
            <!--表格渲染-->
            <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;"
                      @selection-change="crud.selectionChangeHandler">
                <el-table-column type="selection" width="55"/>
                                <el-table-column v-if="columns.visible('id')"
                                                 prop="id"
                                                 label="记录表id"/>
                                <el-table-column v-if="columns.visible('title')"
                                                 prop="title"
                                                 label="积分标题"/>
                                <el-table-column v-if="columns.visible('integralNum')"
                                                 prop="integralNum"
                                                 label="积分数"/>
                                <el-table-column v-if="columns.visible('remark')"
                                                 prop="remark"
                                                 label="备注"/>
                                <el-table-column v-if="columns.visible('createDate')"
                                                 prop="createDate"
                                                 label="创建日期">
                                    <template slot-scope="scope">
                                        <span>{{ parseTime(scope.row.createDate) }}</span>
                                    </template>
                                </el-table-column>
                                <el-table-column v-if="columns.visible('userid')"
                                                 prop="userid"
                                                 label="赠与用户的id"/>
                                <el-table-column v-if="columns.visible('username')"
                                                 prop="username"
                                                 label="赠与用户的名称"/>
                <!-- <el-table-column v-permission="['admin','yxUserIntegral:edit','yxUserIntegral:del']" label="操作"
                                 width="150px" align="center">
                    <template slot-scope="scope">
                        <udOperation
                                :data="scope.row"
                                :permission="permission"
                        />
                    </template>
                </el-table-column> -->
            </el-table>
            <!--分页组件-->
            <pagination/>
        </div>
    </div>
</template>

<script>
    import CRUD, {crud, form, header, presenter} from '@crud/crud'
    import rrOperation from '@crud/RR.operation'
    import crudOperation from '@crud/CRUD.operation'
    import udOperation from '@crud/UD.operation'
    import pagination from '@crud/Pagination'
    import MaterialList from "@/components/material";
    import crudYxUserIntegral from "../../api/yxUserIntegral";
    import { getAll } from '@/api/yxUser'
    // crud交由presenter持有
    const defaultCrud = CRUD({
        title: 'Integral',
        url: 'api/yxUserIntegral',
        sort: 'id,desc',
        crudMethod: {...crudYxUserIntegral}
    })
    const defaultForm = {id: null, title: null, integralNum: null, remark: null, createDate: null, userid: null, username: null}
    export default {
        name: 'YxUserIntegral',
        components: {pagination, crudOperation, rrOperation, udOperation , MaterialList},
        mixins: [presenter(defaultCrud), header(), form(defaultForm), crud()],
        data() {
            return {
                permission: {
                    add: ['admin', 'yxUserIntegral:add'],
                    edit: ['admin', 'yxUserIntegral:edit'],
                    del: ['admin', 'yxUserIntegral:del']
                },
                rules: {
                    
                },
                isedit:false,
                queryTypeOptions: [
                    {
                        key: 'id',
                        display_name: '记录表id'
                    }, 
                    {
                        key: 'title',
                        display_name: '积分标题'
                    }, 
                    {
                        key: 'integralNum',
                        display_name: '积分数'
                    }, 
                    {
                        key: 'remark',
                        display_name: '备注'
                    }, 
                    {
                        key: 'userid',
                        display_name: '赠与用户的id'
                    }
                ],
                userArray:[]
            }
        },
        mounted(){
            var that=this
            getAll().then(res => {
                res.forEach(element => {
                    let temp={}

                        temp.label=element.username
                        temp.value=element.uid  

                    this.userArray.push(temp)
                });
                res.username()
                
            }).catch(err => {
                console.log(err.response.data.message)
            })
        },
        watch: {
        },
        methods: {
            // 获取数据前设置好接口地址
            [CRUD.HOOK.beforeRefresh]() {
                const query = this.query
                if (query.type && query.value) {
                    this.crud.params[query.type] = query.value
                } else {
                    delete this.crud.params.id
                    delete this.crud.params.title
                    delete this.crud.params.integralNum
                    delete this.crud.params.remark
                    delete this.crud.params.userid
                }
                return true
            }, // 新增与编辑前做的操作
            [CRUD.HOOK.afterToCU](crud, form) {
            },
            WOStatusChange(){

            }

        }
    }


</script>

<style scoped>
    .table-img {
        display: inline-block;
        text-align: center;
        background: #ccc;
        color: #fff;
        white-space: nowrap;
        position: relative;
        overflow: hidden;
        vertical-align: middle;
        width: 32px;
        height: 32px;
        line-height: 32px;
    }

</style>
