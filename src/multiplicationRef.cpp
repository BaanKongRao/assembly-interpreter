#include <iostream>

using namespace std;

int multiplication(int x, int y) {
    int result = 0;
    int pivot = 1;
    int shiftCount = 33;
    while (shiftCount > 0) {
        shiftCount--;
        if ((x & pivot) != 0) {
            result += y;
        }
        y <<= 1;
        pivot <<= 1;
    }
    return result;
}

int main() {
    cout << multiplication(32766, 10383) << endl;
    return 0;
}