#include <iostream>

using namespace std;

int fibonacci(int n) {
    int count = n - 1;
    int n_2 = 0;
    int n_1 = 1;
    int result = 0;
    while (count > 0) {
        result = n_1 + n_2;
        n_2 = n_1;
        n_1 = result;
        count--;
    }
    return result;
}

int main() {
    cout << fibonacci(15) << endl;
    return 0;
}