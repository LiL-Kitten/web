$(document).ready(function() {
    const err_msg = $('.error');
    let x = null;
    let y = null;
    let r = null;

    $(".value-x").on('click', function() {
        $(".value-x").removeClass("selected");
        $(this).addClass('selected'); 
        x = $(this).val(); 
        console.log("x = " + x);
    });

    $(".value-y").on('input', function() {
        y = $(this).val(); 
        console.log("y = " + y);
    });

    $("input[name='value-r']").on("change", function() {
        r = $(this).val();
        console.log("r = " + r);
    });

    function normalizeY(y) {
        return y.replace(',', '.');
    }

    function validateY(y) {
        const normalizedY = normalizeY(y);
        const numberY = parseFloat(normalizedY);
        return !isNaN(numberY) && numberY >= -3 && numberY <= 3;
    }

    $('#values').on('submit', function(event) {
        event.preventDefault();

        err_msg.text('');

        if (x === null) {
            err_msg.text("выберите значение X!");
            return; 
        } 
        if (y === null || y.trim() === '') {
            err_msg.text("введите значение Y от -3 до 3.");
            return;
        }
        if (r === null || r === undefined) {
            err_msg.text("выберите значение R!");
            return;
        }

        function formatNumber(num) {
            return (num % 1 === 0) ? Math.round(num) : num;
        }
        
        if (validateY(y)) {

            $.ajax({
                url: 'http://localhost:8080/fcgi-bin/lab-1.jar',
                type: 'POST', 
                dataType: "json",
                data: {
                    x: x,
                    y: normalizeY(y),
                    r: r
                },
                success: function(result) { 
                    $('.output').html(`
                        <p>Дата и время: ${result.now}</p>
                        <p>Координаты: X = ${result.x}, Y = ${result.y}, R = ${result.r}</p>
                        <p>${result.result ? "Попал в ОДЗ." : "Не попал в ОДЗ."}</p>
                    `);
  
                    let format_x = formatNumber(result.x);
                    let format_y = formatNumber(result.y);
                    let format_r = formatNumber(result.r);
            
  
                    const newRow = `
                        <tr>
                            <td>${format_x}</td>
                            <td>${format_y}</td>
                            <td>${format_r}</td>
                            <td>${result.time}</td>
                            <td>${result.date}</td>
                            <td>${result.result ? "Попал" : "Не попал"}</td>
                        </tr>
                    `;
                    $('.history table').append(newRow);
                },
                error: function(xhr, status, error) {
                    err_msg.text(`ошибка при отправке данных: ${xhr.status} ${xhr.statusText}. Попробуйте снова.`);
                }
            });
        } else {
            err_msg.text("координата Y должна быть числом в диапазоне от -3 до 3 и может быть дробным числом с точкой или запятой");
        }
    });
});
