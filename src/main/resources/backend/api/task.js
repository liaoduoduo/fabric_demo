// 获取协同任务详细信息
const getDecideInfoCategoryWithTaskCategory = (id) => {
    return $axios({
        url: '/decide-info-category/getDecideInfoCategoryWithTaskCategory',
        method: 'get',
    })
}