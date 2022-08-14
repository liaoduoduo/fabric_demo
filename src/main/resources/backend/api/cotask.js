// 查询当前用户的协同任务列表接口
const getCotaskPage = params => {
    return $axios({
        url: '/cotasking/getPage',
        method: 'get',
        params,
    });
};

// 新增协同任务并添加情报至协同任务
const addCoTask = params => {
    return $axios({
        url: '/cotasking/save',
        method: 'post',
        data: params,
    });
};
// 添加情报至已有协同任务
const addIntelligenceToCotask = params => {
    return $axios({
        url: '/cotasking-intelligence/addIntelligenceToCotask',
        method: 'post',
        data: params,
    });
};
// 解除情报与协同任务的关联
const deleteCotaskIntelligenceById = id => {
    return $axios({
        url: '/cotasking-intelligence/delete/' + id,
        method: 'delete',
    });
};
// 获取协同任务详细信息
const getCotaskById = id => {
    return $axios({
        url: '/cotasking/getCotaskById/' + id,
        method: 'get',
    });
};
// 根据协同任务id获取当前协同任务包含的情报
const getIntelligencesInCotask = id => {
    return $axios({
        url: '/cotasking-intelligence/getIntelligencesInCotask/' + id,
        method: 'get',
    });
};
// 根据协同任务id获取当前协同任务分配的研判任务
const getTaskInCotask = id => {
    return $axios({
        url: '/task/getTaskByCotaskId/' + id,
        method: 'get',
    });
};
// 修改协同任务信息
const updateCotask = params => {
    return $axios({
        url: '/cotasking/update',
        method: 'put',
        data: params,
    });
};

// 批量删除
const deleteCotaskByIds = ids => {
    return $axios({
        url: '/cotasking/delete',
        method: 'delete',
        // get会将请求参数拼接在url上
        params: { ids },
    });
};
// 修改协同任务状态
const updateCotaskingStatus = params => {
    return $axios({
        url: '/cotasking/updateStatus',
        method: 'put',
        data: params,
    });
};
