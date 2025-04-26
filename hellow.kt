#include <stdio.h>
#include <time.h>
#include <string.h>

int main() {
    time_t now;                 // 时间戳变量
    struct tm *current_time;    // 当前时间结构体指针
    struct tm future_time;      // 未来时间结构体
    int add_days;               // 要增加的天数

    // 获取当前时间戳并转换为本地时间
    time(&now);
    current_time = localtime(&now);

    // 显示当前日期
    printf("当前日期：%d年%d月%d日\n",
           current_time->tm_year + 1900,
           current_time->tm_mon + 1,
           current_time->tm_mday);

    // 中文星期数组
    const char *weekdays[] = {"星期日", "星期一", "星期二", 
                             "星期三", "星期四", "星期五", "星期六"};
    printf("今天是：%s\n", weekdays[current_time->tm_wday]);

    // 获取用户输入的天数
    printf("请输入要增加的天数：");
    if (scanf("%d", &add_days) != 1) {
        printf("输入错误，请重新运行程序！\n");
        return 1;
    }

    // 复制当前时间结构体
    memcpy(&future_time, current_time, sizeof(struct tm));
    
    // 修改天数并自动计算进位
    future_time.tm_mday += add_days;
    future_time.tm_isdst = -1;  // 自动处理夏令时

    // 通过mktime函数自动修正日期
    mktime(&future_time);

    // 显示计算结果
    printf("\n增加%d天后的日期：%d年%d月%d日，%s\n",
           add_days,
           future_time.tm_year + 1900,
           future_time.tm_mon + 1,
           future_time.tm_mday,
           weekdays[future_time.tm_wday]);

    return 0;
}