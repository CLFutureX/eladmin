<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <!--如果想在工具栏加入更多按钮，可以使用插槽方式， slot = 'left' or 'right'-->
      <crudOperation :permission="permission" />
      <!--表单组件-->
      <el-dialog :close-on-click-modal="false" :before-close="crud.cancelCU" :visible.sync="crud.status.cu > 0" :title="crud.status.title" width="500px">
        <el-form ref="form" :model="form" :rules="rules" size="small" label-width="80px">
          <el-form-item label="所属部门">
            未设置字典，请手动设置 Select
          </el-form-item>
          <el-form-item label="客户名称" prop="customName">
            <el-input v-model="form.customName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="客户联系方式" prop="customTel">
            <el-input v-model="form.customTel" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="客户地址">
            <el-input v-model="form.customAddr" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="业务员名称" prop="businessName">
            <el-input v-model="form.businessName" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="类别/系别" prop="type">
            未设置字典，请手动设置 Select
          </el-form-item>
          <el-form-item label="状态" prop="status">
            未设置字典，请手动设置 Select
          </el-form-item>
          <el-form-item label="检查项">
            未设置字典，请手动设置 Select
          </el-form-item>
          <el-form-item label="是否建群">
            未设置字典，请手动设置 Select
          </el-form-item>
          <el-form-item label="清洗情况">
            未设置字典，请手动设置 Select
          </el-form-item>
          <el-form-item label="维修情况">
            未设置字典，请手动设置 Select
          </el-form-item>
          <el-form-item label="安装情况">
            未设置字典，请手动设置 Select
          </el-form-item>
          <el-form-item label="安装单位">
            <el-input v-model="form.installCompany" style="width: 370px;" />
          </el-form-item>
          <el-form-item label="项目经理">
            <el-input v-model="form.projectManager" style="width: 370px;" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="text" @click="crud.cancelCU">取消</el-button>
          <el-button :loading="crud.status.cu === 2" type="primary" @click="crud.submitCU">确认</el-button>
        </div>
      </el-dialog>
      <!--表格渲染-->
      <el-table ref="table" v-loading="crud.loading" :data="crud.data" size="small" style="width: 100%;" @selection-change="crud.selectionChangeHandler">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="createBy" label="创建人" />
        <el-table-column prop="createTime" label="创建日期" />
        <el-table-column prop="updateBy" label="更新人" />
        <el-table-column prop="updateTime" label="更新日期" />
        <el-table-column prop="orgCode" label="所属部门" />
        <el-table-column prop="customName" label="客户名称" />
        <el-table-column prop="customTel" label="客户联系方式" />
        <el-table-column prop="customAddr" label="客户地址" />
        <el-table-column prop="businessName" label="业务员名称" />
        <el-table-column prop="type" label="类别/系别" />
        <el-table-column prop="status" label="状态" />
        <el-table-column prop="checkItem" label="检查项" />
        <el-table-column prop="createGroup" label="是否建群" />
        <el-table-column prop="clearType" label="清洗情况" />
        <el-table-column prop="repairType" label="维修情况" />
        <el-table-column prop="installType" label="安装情况" />
        <el-table-column prop="installCompany" label="安装单位" />
        <el-table-column prop="projectManager" label="项目经理" />
        <el-table-column v-if="checkPer(['admin','customInfo:edit','customInfo:del'])" label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <udOperation
              :data="scope.row"
              :permission="permission"
            />
          </template>
        </el-table-column>
      </el-table>
      <!--分页组件-->
      <pagination />
    </div>
  </div>
</template>

<script>
import crudCustomInfo from '@/api/customInfo'
import CRUD, { presenter, header, form, crud } from '@crud/crud'
import rrOperation from '@crud/RR.operation'
import crudOperation from '@crud/CRUD.operation'
import udOperation from '@crud/UD.operation'
import pagination from '@crud/Pagination'

const defaultForm = { id: null, createBy: null, createTime: null, updateBy: null, updateTime: null, orgCode: null, customName: null, customTel: null, customAddr: null, businessName: null, type: null, status: null, checkItem: null, createGroup: null, clearType: null, repairType: null, installType: null, installCompany: null, projectManager: null }
export default {
  name: 'CustomInfo',
  components: { pagination, crudOperation, rrOperation, udOperation },
  mixins: [presenter(), header(), form(defaultForm), crud()],
  cruds() {
    return CRUD({ title: '客户信息管理', url: 'api/customInfo', idField: 'id', sort: 'id,desc', crudMethod: { ...crudCustomInfo }})
  },
  data() {
    return {
      permission: {
        add: ['admin', 'customInfo:add'],
        edit: ['admin', 'customInfo:edit'],
        del: ['admin', 'customInfo:del']
      },
      rules: {
        id: [
          { required: true, message: 'id主键不能为空', trigger: 'blur' }
        ],
        customName: [
          { required: true, message: '客户名称不能为空', trigger: 'blur' }
        ],
        customTel: [
          { required: true, message: '客户联系方式不能为空', trigger: 'blur' }
        ],
        businessName: [
          { required: true, message: '业务员名称不能为空', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '类别/系别不能为空', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '状态不能为空', trigger: 'blur' }
        ]
      }    }
  },
  methods: {
    // 钩子：在获取表格数据之前执行，false 则代表不获取数据
    [CRUD.HOOK.beforeRefresh]() {
      return true
    }
  }
}
</script>

<style scoped>

</style>
