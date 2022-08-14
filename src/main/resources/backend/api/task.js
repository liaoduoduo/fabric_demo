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
