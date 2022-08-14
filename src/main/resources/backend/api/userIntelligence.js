// 查询列表接口
const getIntelligencePage = params => {
    return $axios({
        url: '/userIntelligence/page',
        method: 'get',
        params,
    });
};

// 文件down预览
const commonDownload = params => {
    return $axios({
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        },
        url: '/common/download',
        method: 'get',
        params,
    });
};

const queryFileHash = row => {
    return $axios({
        url: `/userIntelligence/hash`,
        method: 'post',
        data: row,
    });
};
