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
    // var ids = window.Qs.stringify({ ids: params }, { indices: false });
    // console.log(ids);
    return $axios({
        url: '/task/deleteTask',
        method: 'delete',
        // params: params,
        data: ids,
    });
};
