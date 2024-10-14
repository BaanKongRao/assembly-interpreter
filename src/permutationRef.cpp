#include <iostream>

using namespace std;

int permutation(int n, int r) {
    if (r == 0) {
        return 1;
    } else {
        return n * permutation(n - 1, r - 1);
    }
}

int main() {
    cout << permutation(5, 3) << endl;
    return 0;
}