export const mapOption = {
    basicOption: {
        tooltip: {
            trigger: 'item',
            formatter: '{b}'
        },
        //左侧小导航图标
        visualMap: {
            pieces: [
                { min: 0, max: 1, label: '已提交', color: '#2c9a42' },
                { min: -1, max: 0, label: '未提交', color: '#d08a00' },
            ],
            textStyle: {
                color: '#fff',
            },
            top: 50,
            left: 30,
        },
        geo: {
            top: 100
            // layoutCenter: ['30%', '30%'],
            // // 如果宽高比大于 1 则宽度为 100，如果小于 1 则高度为 100，保证了不超过 100x100 的区域
            // layoutSize: 100
            // 设置这两个值后 left/right/top/bottom/width/height 无效。
        }
    },
    seriesOption: {
        type: 'map',
        roam: false,
        nameMap: {
            'china': '中国'
        },
        label: {
            normal: {
                show: true,
                textStyle: {
                    color: '#fff',
                }
            },
            emphasis: {
                show: true,
                textStyle: {
                    color: '#fff',
                }
            }
        },
        itemStyle: {
            normal: {
                color: '#fff',
                areaColor: '#000',
                borderColor: '#fff',
                borderWidth: 0.5,
                label: {
                    show: true,
                    textStyle: {
                        color: "rgb(249, 249, 249)",
                    }
                },
            },
            emphasis: {
                areaColor: true,
            }
        },
    }
}
// 饼图
export const pieOption =
{
    title: {
        text: '未安全佩戴头盔分布',
        subtext: '2024-04',
        left: 'center'
    },
    tooltip: {
        trigger: 'item'
    },
    legend: {
        show: false,
        orient: 'vertical',
        left: 'left'
    },
    series: [
        {
            name: '来源地',
            type: 'pie',
            radius: '50%',
            data: [
                { value: 1048, name: '测试' },
            ],
            itemStyle: {
                normal: {
                    label: {
                    }
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
