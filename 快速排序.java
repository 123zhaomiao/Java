package 排序.快速排序;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.Arrays;

/**
 * 快速排序算法的基本思想是:
 *
 * 任取待排序序列中的某个元素作为基准值，按照基准值将待排序集合分割成两个子序列
 * 左子序列中的所有元素小于基准值，右子序列中的所有元素均大于基准值
 * 然后对左右子序列重复该过程 直到所有的元素都排列在相应位置上为止
 *
 * 一般地我们会选择待排序序列中的最后一个元素作为基准值
 * 但是如果选中的基准值过大或者过小，会使左子序列和右子序列的元素个数相差反而巨大影响效率
 * 所以对于基准值的选择我们一般采用三数取中法(选取序列开始结点、序列中间结点、序列末尾结点取中间值)
 *
 * 快速排序适用于：数据量大且越随机越好但是若待排序的序列较长，使用递归的方法一层一层调用极易造成栈溢出这时我们可以采用循环
 */
public class QuickSort {
    public static void main(String[] args) {
        //1.待排序序列
        int[] arr = new int[]{6,5,2,3,9,8,1,4,7,123,32,21,1,2,4,6,4};
        //2.调用for-each循环 输出排序前
        System.out.print("排序前:");
        for(int i:arr){
            System.out.print(i+" ");
        }
        //3.调用快速排序的方法 并且传入待排序序列的下标
        quickSort(arr,0, arr.length-1);
        //4.调用for-each循环 输出排序后
        System.out.println();
        System.out.print("排序后:");
        for(int i:arr){
            System.out.print(i+" ");
        }
    }
    private static void quickSort(int[]arr,int begin,int end){
        //1.调用快排的具体实现 返回值为基准值所在位置的小标
        int position = quickSortMethod(arr,begin,end);

        if(begin < position-1){
            quickSort(arr,begin,position-1);
        }
        if(end > position+1){
            quickSort(arr,position+1,end);
        }
    }
//    /**
//     * Hoare版  以升序为例
//     * 1.设置两个下标begin、end 分别指向待排序序列的两端
//     * 2.begin从待排序序列的 最左边开始向右边遍历 ，找到比基准值大的停止
//     *   end从待排序序列的最右边开始向左边遍历，找到比基准值小的停止
//     *   如果begin != end则将两个数交换 继续向后比较  直到begin==end
//     * 3.begin==end之后将下标为begin的数和基准值交换
//     * 一次快排就完成了
//     * @param arr 待排序序列
//     * @param begin  待排序序列从下标为begin开始
//     * @param end 待排序序列到下标为end开始
//     */
//    private static int quickSortMethod(int[]arr,int begin,int end){
//        //1.三数取中法获取基准值 返回基准值的下标
//        int key = threeNumberFindMiddle(arr,begin,end);
//        //如果基准值不是最后一位则交换
//        if(key != end){
//            swap(arr,key,end);
//            key = end;
//        }
//        while(begin != end){
//            //2.begin从待排序序列的 最左边开始向右边遍历 ，找到比基准值大的停止
//            //为防止在内部循环越界 时刻保持begin<end
//            while(arr[begin] <= arr[key] && begin < end){
//                begin++;
//            }
//            //3.end从待排序序列的最右边开始向左边遍历，找到比基准值小的停止
//            while(arr[end] >= arr[key] && begin < end){
//                end--;
//            }
//            //4.如果begin != end则将两个数交换
//            if(begin != end){
//                swap(arr,begin,end);
//            }
//            //5.继续向后比较  直到begin==end
//        }
//        //6.此时begin == end  将基准值与begin下标对应的元素交换
//        if(begin != key){
//            swap(arr,begin,key);
//        }
//        return begin;
//    }
//
//    /**
//     * 挖坑版本
//     * 基本上与Hoare版本类似
//     * 1.将基准值的元素保存起来，并将该处当做一个坑
//     * 2.begin从待排序序列的 最左边开始向右边遍历 ，找到比基准值大的停止，将该元素填入坑，并将begin处当做坑
//     * 3.end从待排序序列的最右边开始向左边遍历，找到比基准值小的停止，将该元素填入坑，并将end处当做坑
//     * 4.直到begin==end 一次快排完成
//     * 5.将基本值填入此时begin所对应的坑
//     * @param arr 待排序序列
//     * @param begin 待排序序列从下标为begin开始
//     * @param end 待排序序列到下标为end开始
//     * @return
//     */
//    private static int quickSortMethod(int[]arr,int begin,int end){
//        int key = threeNumberFindMiddle(arr,begin,end);
//        if(key!=end){
//            swap(arr,key,end);
//            key = end;
//        }
//        //1.将基准值保存起来
//        int element = arr[key];
//        while(begin!=end){
//            //2.begin从待排序序列的 最左边开始向右边遍历 ，找到比基准值大的停止
//            //为防止在内部循环越界 时刻保持begin<end
//            while(arr[begin] <= element && begin < end){
//                begin++;
//            }
//            if(begin != end){
//                //3.该元素填入坑，并将begin处当做坑
//                arr[end] = arr[begin];
//                //没有这个语句也不会影响结果，因为既然交换了那么begin所对应的值一定比基准值大 加上该语句下次循环可以少判断一次
//                end--;
//            }
//            //4.end从待排序序列的最右边开始向左边遍历，找到比基准值小的停止
//            while(arr[end] >= element && begin < end){
//                end--;
//            }
//            //5.将该元素填入坑
//            if(begin != end){
//                arr[begin] = arr[end];
//                //没有这个语句也不会影响结果，因为既然交换了那么end所对应的值一定比基准值小 加上该语句下次循环可以少判断一次
//                begin++;
//            }
//        }
//        if(begin != key){
//            arr[begin] = element;
//        }
//        return begin;
//    }

    /**
     * 前后下标法
     * 1.设置两个下标 begin,cur  cur = begin-1
     * 2.判断arr[begin]是否比基准值大
     *   若arr[begin]不大于基准值 cur begin 一起右移
     *   若arr[begin]大于基准值 begin右移
     *   遇到比基准值小的 begin与cur+1位置上的元素交换 begin++
     * 3. 直到begin超出范围一次快排结束
     * @param arr 待排序序列
     * @param begin 待排序序列从下标为begin开始
     * @param end 待排序序列到下标为end开始
     * @return
     */
    private static int quickSortMethod(int[]arr,int begin,int end){
        int key = threeNumberFindMiddle(arr,begin,end);
        if(key!=end){
            swap(arr,key,end);
            key = end;
        }
        int cur = begin -1 ;
        //begin超出范围快排结束
        while(begin <= end){
            if(arr[begin] <= arr[key] && ++cur!=begin){
                swap(arr,begin,cur);
            }
            begin++;
        }
        return cur;
    }
    private static int threeNumberFindMiddle(int[]arr,int begin,int end){
        int [] threeNumber=  new int[3];
        threeNumber[0] = arr[begin];
        threeNumber[1] = arr[end];
        threeNumber[2] = arr[(end-begin)/2];
        //1.排序
        Arrays.sort(threeNumber);

        //2.下标为1即为中间值
        if(arr[begin] == threeNumber[1]){
            return begin;
        }else if(arr[end] == threeNumber[1]){
            return end;
        }else{
            return (end-begin)/2;
        }
    }
    private static void swap(int[]arr,int begin,int end){
        int temp = arr[begin];
        arr[begin] = arr[end];
        arr[end] = temp;
    }
}
