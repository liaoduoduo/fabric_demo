// import qs from '../plugins/qs';
// 获取研判任务分类信息
const getDecideInfoCategoryWithTaskCategory = id => {
    return $axios({
        url: '/decide-info-category/getDecideInfoCategoryWithTaskCategory',
        method: 'get',
    });
};
// 为协同任务添加研判任务
const addTask = params => {
    return $axios({
        url: '/task/addTask',
        method: 'post',
        data: params,
    });
};
// 增加悬赏
const updateTaskTokenMethod = params => {
    return $axios({
        url: '/task/updateToken',
        method: 'put',
        data: params,
    });
};
// 撤销悬赏任务
const deleteTaskByIdsMethod = ids => {
    return $axios({
        url: '/task/deleteTask',
        method: 'delete',
        data: ids,
    });
};
const updateTaskStatusMethod = params => {
    return $axios({
        url: '/task/updateStatus',
        method: 'put',
        data: params,
    });
};
//获取悬赏任务分页
const getRewardTaskPage = params => {
    return $axios({
        url: '/task/getRewardTaskPage',
        method: 'get',
        params: params,
    });
};

//承接悬赏任务
const RewardTaskAcceptToList = id =>{
    return $axios({
        url: '/user-task/acceptTask/'+id,
        method: 'post',
        //data: id,
    });
};

//获取已承接研判任务分页
const getAcceptedTaskPage = params => {
    return $axios({
        url: '/user-task/getUserTaskByUserId',
        method: 'get',
        params: params,
    });
};

//获取悬赏任务完成过程中应填的字段的名称
const getRewardTaskContent = id =>{
    return $axios({
        url: '/decide-info/getTaskFieldById/'+id,
        method: 'get',
    });
};
