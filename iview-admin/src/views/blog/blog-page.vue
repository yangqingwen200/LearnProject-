<style lang="less">
    @import '../../styles/common.less';
    @import './blog-page.less';
</style>

<template>
    <div>
        <Card>
            <Row>
                <Col span="18">
                    <Input v-model="searchTitle" placeholder="请输入标题搜搜..." style="width: 200px" />
                    <Input v-model="searchContent" placeholder="请输入内容搜搜..." style="width: 200px" />
                    <span @click="handleSearch" style="margin: 0 10px;"><Button type="primary" icon="search">搜索</Button></span>
                    <Button @click="handleCancel" type="ghost" >取消</Button>
                </Col>
                <Col span="6" class="text-right">
                    <Button @click="create" type="ghost" icon="plus">创建</Button>
                    <Button @click="deleteChoose" type="error" icon="trash-a">删除</Button>
                </Col>
            </Row>
            <Row class="margin-top-10 searchable-table-con1">
                <Table ref="selection" @on-selection-change="selectChange" @on-select-all="selectChange" :columns="columns" stripe border :data="data"></Table>
            </Row>
            <Row class="margin-top-10">
                <Page :total="totalRow" @on-change="changePage" show-total show-elevator show-sizer></Page>
            </Row>
        </Card>
        <Modal
            v-model="formModel"
            width="800"
            title="添加/编辑">
            <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                <FormItem label="标题" prop="title">
                    <Input v-model="formValidate.title" placeholder="请输入标题..."></Input>
                </FormItem>
                <FormItem label="内容" prop="content">
                    <Input v-model="formValidate.content" type="textarea" :autosize="{minRows: 2,maxRows: 15}" placeholder="请输入内容..."></Input>
                </FormItem>
            </Form>
            <p slot="footer">
                <Button type="primary" @click="handleSubmit('formValidate')">提交</Button>
                <Button type="ghost" @click="handleReset('formValidate')" style="margin-left: 8px">关闭</Button>
            </p>
        </Modal>
    </div>
</template>

<script>
export default {
    name: 'Blog',
    data () {
        return {
            formModel: false,
            searchTitle: '',
            searchContent: '',
            pageNumber: 1,
            pageSize: 10,
            totalRow: 0,
            modelType: 'add',
            selectData: [],
            columns: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },{
                    type: 'index',
                    width: 80,
                    title: '序号',
                    align: 'center'
                },
                {
                    key: 'title',
                    align: 'center',
                    title: '标题'
                },
                {
                    key: 'content',
                    align: 'center',
                    ellipsis: true,
                    title: '内容'
                },
                {
                    title: '操作',
                    key: 'action',
                    align: 'center',
                    //width: 120,
                    render: (h, params) => {
                        return h('div', [
                            h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        this.show(params.index)
                                    }
                                }
                            }, '查看'),
                            h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        this.edit(params.index)
                                    }
                                }
                            }, '修改'),
                            h('Button', {
                                props: {
                                    type: 'error',
                                    size: 'small'
                                },
                                on: {
                                    click: () => {
                                        this.deleteBefore(params.index)
                                    }
                                }
                            }, '删除')
                        ]);
                    }
                }
            ],
            data: [],
            initTable: [],
            formValidate: {
                id: '',
                title: '',
                content: ''
            },
            ruleValidate: {
                title: [
                    { required: true, message: '标题不能为空！', trigger: 'blur' }
                ],
                content: [
                    { required: true, message: '内容不能为空！', trigger: 'blur' },
                    { type: 'string', min: 20, message: '请输入至少20字以上！', trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        init() {
            let _this = this;
            this.$http({
                method:'GET',
                url:'/api/blog/list',
                params: {
                    'pageNumber': this.pageNumber,
                    'pageSize': this.pageSize
                },
                emulateJSON: true
            }).then((response)=>{
                _this.data = _this.initTable = response.data.list;
                _this.totalRow = response.data.totalRow;
            },(error)=> {
                this.$Message.error('Fail!');
            });
        },
        changePage (page){
            this.pageNumber = page;
            this.init();
        },
        selectChange (selection){
            this.selectData = [];
            for (let n in selection) {
                this.selectData.push(selection[n].id);
            }
            //this.selectData = selection;
            console.log(this.selectData)
        },
        search (data, argumentObj) {
            let res = data;
            let dataClone = data;
            for (let argu in argumentObj) {
                if (argumentObj[argu].length > 0) {
                    res = dataClone.filter(d => {
                        return d[argu].indexOf(argumentObj[argu]) > -1;
                    });
                    dataClone = res;
                }
            }
            return res;
        },
        handleSearch () {
            this.data = this.initTable;
            this.data = this.search(this.data, {title: this.searchTitle});
            this.data = this.search(this.data, {content: this.searchContent});
        },
        handleCancel () {
            this.searchTitle = '';
            this.searchContent = '';
            this.data = this.initTable;
        },
        show (index) {
            this.$Modal.info({
                title: '详细信息',
                width: '800',
                content: `<p class="text-center title">${this.data[index].title}</p><p class="text-content">${this.data[index].content}</p>`
            })
        },
        edit (index) {
            this.$refs['formValidate'].resetFields();
            this.formValidate.title = this.data[index].title;
            this.formValidate.content = this.data[index].content;
            this.formValidate.id = this.data[index].id;
            this.formModel = true;
            this.modelType = 'edit';
        },
        deleteBefore (index) {
            this.$Modal.confirm({
                title: '删除内容',
                content: '您确认要删除该条内容吗？',
                onOk: () => {
                    this.delete(index);
                }
            });
        },
        delete (index) {
            this.$http({
                method:'GET',
                url:'/api/blog/delete/'+this.data[index].id,
                emulateJSON: true
            }).then((response)=>{
                if(response.body.success){
                    this.init();
                    this.$Message.success(response.body.message);
                }else{
                    this.$Message.error(response.body.message);
                }
            },(error)=> {
                this.$Message.error('删除错误!');
            });
        },
        create () {
            this.$refs['formValidate'].resetFields();
            this.formModel = true;
            this.modelType = 'add';
        },
        deleteChoose () {
            if(this.selectData.length==0){
                this.$Modal.warning({
                    title: '提示信息',
                    content: '请选择一条记录进行删除!'
                });
            }else{
                this.$http({
                    method:'GET',
                    url: '/api/blog/deletes',
                    params: {
                        'ids': JSON.stringify(this.selectData)
                    },
                    emulateJSON: true
                }).then((response)=>{
                    if(response.body.success){
                        this.init();
                        this.$Message.success(response.body.message);
                    }else{
                        this.$Message.error(response.body.message);
                    }
                },(error)=> {
                    this.$Message.error('Fail!');
                });
            }
        },
        handleSubmit (name) {
            this.$refs[name].validate((valid) => {
                if (valid) {
                    let url;
                    if(this.modelType=='add'){
                        url = '/api/blog/save';
                    } else {
                        url = '/api/blog/update';
                    }  	
		        	this.$http({
	                    method:'GET',
	                    url: url,
	                    params: {
	                    	'blog.id': this.formValidate.id,
	                    	'blog.title': this.formValidate.title,
	                    	'blog.content': this.formValidate.content
	                    },
	                    emulateJSON: true
                   	}).then((response)=>{
                    	if(response.body.success){
                            this.handleReset(name);
                            this.init();
                            this.$Message.success(response.body.message);
                        }else{
                            this.$Message.error(response.body.message);
                        }
                    },(error)=> {
		                this.$Message.error('Fail!');
		            });
                }
            })
        },
        handleReset (name) {
            this.$refs[name].resetFields();
            this.formModel = false;
        }
    },
    mounted () {
        this.init();
    }
};
</script>
