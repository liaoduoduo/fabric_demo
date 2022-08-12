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
